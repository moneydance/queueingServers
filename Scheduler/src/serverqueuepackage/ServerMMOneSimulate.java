package serverqueuepackage;

import serverpackage.*;
import serverpackage.eventpackage.*;
import java.util.List;
import java.util.PriorityQueue;

public class ServerMMOneSimulate extends Simulate
{
    private PriorityQueue<Event> calander;
    private double max_time;
    private ServerFifo server;
    private double clock;

    public ServerMMOneSimulate(double max_time, boolean record_logs)
    {
        clock = 0.0;
        calander = new PriorityQueue<Event>();
        server = new ServerFifo(record_logs);
        calander.add(new EventBirth(clock));
        calander.add(new EventMonitor(1, max_time));
        this.max_time = max_time;
    }

    public void run()
    {
        while(clock<max_time * 2)
        {
            Event current_event = calander.poll();
            clock = current_event.getTimeStamp();
            resolveEvent(current_event);
        }
        server.stats.printStats(clock, max_time);
    }

    private void resolveEvent(Event current_event)
    {
        if (current_event instanceof EventBirth)
        {
           List<Event> new_events = server.arrival(current_event, clock);
           addEvents(new_events);
        }
        else if (current_event instanceof EventDeath)
        {
            if (clock > max_time)
                server.stats.recordTimes(current_event.getTask());
            Event new_death_event = server.departure(clock);
            if (new_death_event != null)
                calander.add(new_death_event);
        }
        else if (current_event instanceof EventMonitor)
        {
            Event new_monitor = server.monitor(clock, max_time);
            calander.add(new_monitor);
        }
    }

    private void addEvents(List<Event> new_events)
    {
        for (Event event : new_events)
            calander.add(event);
    }
}
