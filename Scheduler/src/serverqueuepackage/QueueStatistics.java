package serverqueuepackage;

import serverpackage.*;

public class QueueStatistics extends Statistics
{
    private int death_event_count_io;
    private double total_time_io;
    private int death_event_count_cpu;
    private double total_time_cpu;
    private double total_service_time;

    public QueueStatistics(String server_type, boolean collect_logs)
    {
        super(server_type, collect_logs);
        death_event_count_io = 0;
        death_event_count_cpu = 0;
        total_time_io = 0;
        total_time_cpu = 0;
        total_service_time = 0;
    }

    public void recordTimes(Task task)
    {
        double wait_time = task.getWaitTime();
        double service_time = task.getServiceTime();
        double total_time = service_time + wait_time;
        total_service_time += service_time;
        int task_type = task.getTaskType();
        if(task_type == 0)
        {
            death_event_count_io++;
            total_time_io += total_time;
        }
        else
        {
            death_event_count_cpu++;
            total_time_cpu += total_time;
        }
    }

    public void recordMonitor()
    {
        monitor_event_count++;
    }

    public void printStats(double clock, double max_time)
    {
        System.out.println("===================================================");
        double slowdown_io = total_time_io/(death_event_count_io * .01);
        double slowdown_cpu = total_time_cpu/(death_event_count_cpu * .3);
        System.out.println("CLOCK: " + (clock - max_time));
        System.out.println("Rho: " + (total_service_time)/(clock - max_time));
        System.out.println("IO slowdown " + slowdown_io);
        System.out.println("CPU slowdown " + slowdown_cpu);
        System.out.println("IO Tasks through system: " + death_event_count_io);
        System.out.println("CPU Tasks through system: " + death_event_count_cpu);
        System.out.println("===================================================");
    }
}
