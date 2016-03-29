package serverqueuepackage;

import serverpackage.*;
import serverpackage.eventpackage.*;
import java.util.List;

import queuepackage.*;

import java.util.ArrayList;

public class ServerHsn extends Server
{
    private Hsn task_queue;
    private double monitor_rate;
    private static double time_quantom = .01;

    public ServerHsn(boolean record_logs)
    {
        task_queue = new Hsn();
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
            new_events.add(cycleOrDeath(arriving_task, clock));
        }
        task_queue.enqueue(arriving_task);
        new_events.add(new EventBirth(clock, arriving_task.getTaskType()));
        return new_events;
    }

    public Event departure(double clock)
    {
        task_queue.taskFinished();
        if (task_queue.getQueueLength() > 0)
        {
            Task departing_task = task_queue.dequeue(clock);
            departing_task.updateWaitTime(clock);
            return cycleOrDeath(departing_task, clock);
        }
        return null;
    }

    public Event cycle(double clock)
    {
        Task cycling_task = task_queue.taskFinished();
        cycling_task.updateArrivalTime(clock);
        double next_service_time = cycling_task.getRemainingServiceTime() - time_quantom;
        cycling_task.updateServiceTime(next_service_time);
        task_queue.enqueue(cycling_task);
        if (task_queue.getQueueLength() > 0)
        {
            Task departing_task = task_queue.dequeue(clock);
            departing_task.updateWaitTime(clock);
            return cycleOrDeath(departing_task, clock);
        }
        return null;
    }

    private Event cycleOrDeath(Task task, double clock)
    {
        Event return_event;
        double next_service_time = Math.max(task.getRemainingServiceTime() - time_quantom, 0.0);
        if (next_service_time == 0)
        {
            return_event = new EventDeath(task, clock);
        }
        else
        {
            return_event = new EventCycle(task, clock, time_quantom);
        }
        return return_event;
    }

    public Event monitor(double clock, double max_time)
    {
        stats.writeStats(clock, max_time);
        return new EventMonitor(monitor_rate, clock);
    }
}

