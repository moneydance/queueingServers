package serverpackage;

public class Task implements Comparable<Task>{
    private double[] arrival_time;
    private double[] service_time;
    private double wait_time;
    private int task_type;
    private double task_start_time;

    public Task(double arrival_time, double service_time, int task_type)
    {
        // tasks have initial arrival and service time but can be changed
        // via interuption so index 0 keeps initial values and index 1
        // keeps most recent values.
        this.arrival_time = new double[2];
        this.arrival_time[0] = arrival_time;
        this.arrival_time[1] = arrival_time;
        this.service_time = new double[2];
        this.service_time[0] = service_time;
        this.service_time[1] = service_time;
        this.wait_time = 0;
        this.task_type = task_type;
    }

    public int compareTo(Task o2)
    {
        if (service_time[1] > o2.service_time[1])
            return 1;
        else if (service_time[1] < o2.service_time[1])
            return -1;
        else
            return 0;
    }

    public void updateArrivalTime(double new_arrival_time)
    {
        this.arrival_time[1] = new_arrival_time;
    }

    public void updateServiceTime(double new_service_time)
    {
        this.service_time[1] = new_service_time;
    }

    public void updateWaitTime(double clock)
    {
        this.task_start_time = clock;
        this.wait_time += clock - arrival_time[1];
    }

    public double getStartTime()
    {
        return task_start_time;
    }

    public double getServiceTime()
    {
        return service_time[0];
    }

    public double getRemainingServiceTime()
    {
        return service_time[1];
    }

    public double getWaitTime()
    {
        return wait_time;
    }

    public double getArrivalTime()
    {
        return arrival_time[0];
    }

    public double getRecentArrivalTime()
    {
        return arrival_time[1];
    }

    public int getTaskType()
    {
        return task_type;
    }
}
