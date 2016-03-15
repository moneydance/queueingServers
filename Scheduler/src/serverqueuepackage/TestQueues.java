package serverqueuepackage;

import serverpackage.*;

public class TestQueues{
    public static void main (String[] args)
    {
        Simulate sim = new ServerMMOneSimulate(100, true);
        sim.run();
    }
}
