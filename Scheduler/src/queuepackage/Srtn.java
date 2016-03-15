package queuepackage;

import serverpackage.*;

import java.util.PriorityQueue;

public class Srtn extends Queue
{
    private PriorityQueue<Task> task_queue;
    public Srtn()
    {
        task_queue = new PriorityQueue<Task>();
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

    public void taskFinished()
    {
        queue_length--;
        task_queue.poll();
    }
}

