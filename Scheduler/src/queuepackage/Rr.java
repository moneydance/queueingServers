package queuepackage;

import serverpackage.*;
import java.util.LinkedList;

public class Rr extends Queue
{
    private LinkedList<Task> task_queue;
    public Rr()
    {
        task_queue = new LinkedList<Task>();
        queue_length = 0;
    }

    public void enqueue(Task t)
    {
        task_queue.add(t);
        queue_length++;
    }

    public Task dequeue()
    {
        return task_queue.peek();
    }

    public Task taskFinished()
    {
        queue_length--;
        return task_queue.remove();
    }
}

