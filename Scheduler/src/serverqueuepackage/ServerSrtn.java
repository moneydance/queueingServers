package serverqueuepackage;

import serverpackage.*;
import serverpackage.eventpackage.*;
import java.util.List;

import queuepackage.*;

import java.util.ArrayList;

public class ServerSrtn extends Server
{
    private Srtn task_queue;
    private double monitor_rate;
    private EventDeath next_event_death;

    public ServerSrtn(boolean record_logs)
    {
        task_queue = new Srtn();
        this.monitor_rate = 1;
        stats = new QueueStatistics(getServerType(), record_logs);
    }

    public int getQueueLength()
    {
        return Math.max(0, task_queue.getQueueLength() - 1);
    }

    public int getSystemLength()
    {
        return task_queue.getQueueLength();
    }

    public List<Event> arrival(Event event, double clock)
    {
        List<Event> new_events = new ArrayList<Event>();
        Task arriving_task = event.getTask();
        if (task_queue.getQueueLength() == 0)
        {
            arriving_task.updateWaitTime(clock);
            next_event_death = new EventDeath(arriving_task, clock);
            new_events.add(next_event_death);
        }
        EventDeath new_death_event = task_queue.enqueue(arriving_task, clock);
        if (new_death_event != null)
        {
           new_events.add(new_death_event);
           Simulate.calander.remove(next_event_death);
           next_event_death = new_death_event;
        }
        new_events.add(new EventBirth(clock, arriving_task.getTaskType()));
        return new_events;
    }

    public Event departure(double clock)
    {
        task_queue.taskFinished();
        if (task_queue.getQueueLength() > 0)
        {
            Task departing_task = task_queue.dequeue();
            departing_task.updateWaitTime(clock);
            next_event_death = new EventDeath(departing_task, clock);
            return next_event_death;
        }
        return null;
    }

    public Event monitor(double clock, double max_time)
    {
        stats.recordMonitor();
        stats.writeStats(clock, max_time);
        return new EventMonitor(monitor_rate, clock);
    }

    public Event cycle(double clock)
    {
        return null;
    }
}
