package queuepackage;

import serverpackage.*;

public abstract class Queue
{
    protected int queue_length;
    public abstract void enqueue(Task t);
    public abstract Task dequeue();
    public abstract void taskFinished();
    public int getQueueLength()
    {
        return queue_length;
    }
}

