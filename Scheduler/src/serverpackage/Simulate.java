package serverpackage;

import serverpackage.eventpackage.*;
import java.util.PriorityQueue;

public abstract class Simulate
{
    public static PriorityQueue<Event> calander;
    private double max_time;
    private Server server;
    private Statistics stats;
    private double clock;

    abstract public void run();
}
