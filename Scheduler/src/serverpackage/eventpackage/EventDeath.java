package serverpackage.eventpackage;
import serverpackage.Task;

public class EventDeath extends Event
{
    private static double MU_IO = 1/.01;
    private static double MU_CPU = 1/.3;
    public EventDeath(Task task, double clock)
    {
        double service_time;
        int task_type = task.getTaskType();
        if (task_type == 0)
            service_time = nextExponential(MU_IO);
        else
            service_time = nextExponential(MU_CPU);
        task.updateServiceTime(service_time);
        this.time_stamp = clock + service_time;
        this.task = task;
    }
}
