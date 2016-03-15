package serverpackage.eventpackage;

import serverpackage.Task;


public class EventBirth extends Event
{
    public static double LAMBDA_IO = 6;
    public static double LAMBDA_CPU = 3;
    public EventBirth(double clock)
    {
        double time_step;
        int task_flag;
        if (Math.random() < .5)
        {
            task_flag = 0;
            time_step = nextExponential(LAMBDA_IO);
        }
        else
            task_flag =1;
            time_step = nextExponential(LAMBDA_CPU);
        this.time_stamp = clock + time_step;
        this.task = new Task(time_stamp, task_flag);
    }

    public EventBirth(Task task, double time_step, double clock){
        this.task = task;
        this.time_stamp = clock + time_step;
    }
}
