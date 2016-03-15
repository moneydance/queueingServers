package serverpackage;

public class Task implements Comparable<Task>{
    private double arrival_time;
    private double service_time;
    private double wait_time;
    private int task_type;

    public Task(double arrival_time, int task_type)
    {
        this.arrival_time = arrival_time;
        this.task_type = task_type;
    }

    public int compareTo(Task o2)
    {
        if (service_time > o2.service_time)
            return 1;
        else if (service_time < o2.service_time)
            return -1;
        else
            return 0;
    }

    public void updateServiceTime(double service_time)
    {
        this.service_time = service_time;
    }

    public void updateWaitTime(double clock)
    {
        this.wait_time = clock - arrival_time;
    }

    public double getServiceTime()
    {
        return service_time;
    }

    public double getWaitTime()
    {
        return wait_time;
    }
    public double getArrivalTime()
    {
        return arrival_time;
    }

    public int getTaskType()
    {
        return task_type;
    }
}
