package serverpackage.eventpackage;
import serverpackage.Task;

public class EventDeath extends Event
{
    public EventDeath(Task task, double clock)
    {
        double service_time = task.getRemainingServiceTime();
        this.time_stamp = clock + service_time;
        this.task = task;
    }
}
