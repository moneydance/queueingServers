package queuepackage;

import serverpackage.*;
import serverpackage.eventpackage.*;

import java.util.PriorityQueue;

public class Srtn extends Queue
{
    private PriorityQueue<Task> task_queue;
    public Srtn()
    {
        task_queue = new PriorityQueue<Task>();
        queue_length = 0;
    }

    public EventDeath enqueue(Task t, double clock)
    {
        EventDeath new_death = null;
        Task current_task = task_queue.poll();
        if (current_task != null)
        {
            double task_time_serviced = clock - current_task.getStartTime();
            double task_remaining_time = current_task.getRemainingServiceTime() - task_time_serviced;
            current_task.updateServiceTime(task_remaining_time);
            if (current_task.compareTo(t) == 1)
            {
                current_task.updateArrivalTime(clock);
                t.updateWaitTime(clock);
                new_death = new EventDeath(t, clock);
            }
            task_queue.add(current_task);
        }
        task_queue.add(t);
        queue_length++;
        return new_death;
    }

    public Task dequeue()
    {
        return task_queue.peek();
    }

    public void taskFinished()
    {
        queue_length--;
        task_queue.poll();
    }
}

