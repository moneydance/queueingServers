package serverpackage.eventpackage;
import serverpackage.Task;

public class EventCycle extends Event
{
    public EventCycle(Task task, double clock, double time_serviced)
    {
        this.time_stamp = clock + time_serviced;
        this.task = task;
    }
}
