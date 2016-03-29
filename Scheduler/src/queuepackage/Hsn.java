package queuepackage;

import serverpackage.*;
import java.util.ArrayList;

public class Hsn extends Queue
{
    private ArrayList<Task> task_queue;
    private Task current_task;
    public Hsn()
    {
        task_queue = new ArrayList<Task>();
        queue_length = 0;
    }

    public void enqueue(Task t)
    {
        if (queue_length == 0)
        {
            current_task = t;
        }
        task_queue.add(t);
        queue_length++;
    }

    public Task dequeue(double clock)
    {
        Task max_slowdown_task = null;
        double max_slowdown = -1000.0;
        for(Task task: task_queue)
        {
            double task_slowdown = calculateSlowdown(clock, task);
            if (task_slowdown > max_slowdown)
            {
                max_slowdown = task_slowdown;
                max_slowdown_task = task;
            }
        }
        current_task = max_slowdown_task;
        return max_slowdown_task;
    }

    private double calculateSlowdown(double clock, Task task)
    {
        double remaining_service_time = task.getRemainingServiceTime();
        double original_service_time = task.getServiceTime();
        return (clock - task.getArrivalTime() + remaining_service_time)/original_service_time;
    }

    public Task taskFinished()
    {
        queue_length--;
        task_queue.remove(current_task);
        Task temp = current_task;
        current_task = null;
        return temp;
    }
}
