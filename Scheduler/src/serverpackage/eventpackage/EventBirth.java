package serverpackage.eventpackage;

import serverpackage.Task;


public class EventBirth extends Event
{
    private static double LAMBDA_IO;
    private static double LAMBDA_CPU;

    public static double MU_IO = 1/.01;
    public static double MU_CPU = 1/.3;

    public static void setLambdas(double lambda_io, double lambda_cpu)
    {
        LAMBDA_IO = lambda_io;
        LAMBDA_CPU = lambda_cpu;
    }

    public EventBirth(double clock, int flag)
    {
        double time_step;
        double service_time;
        if (flag == 0)
        {
            time_step = nextExponential(LAMBDA_IO);
            service_time = nextExponential(MU_IO);
        }
        else
        {
            time_step = nextExponential(LAMBDA_CPU);
            service_time = nextExponential(MU_CPU);
        }
        this.time_stamp = clock + time_step;
        this.task = new Task(time_stamp, service_time, flag);
    }
}
