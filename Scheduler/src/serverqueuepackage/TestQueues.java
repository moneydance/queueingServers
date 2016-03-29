package serverqueuepackage;

import serverpackage.*;

public class TestQueues{
    public static void main (String[] args)
    {
        Simulate sim_low_load;
        Simulate sim_test_load;

        String srvr1 = "F";
        System.out.println("FIFO");
        System.out.println("Low Lambdas");
        sim_low_load = new ServerMMOneSimulate(.06, .03, 100000, srvr1, false);
        sim_low_load.run();
        System.out.println("Normal Lambda");
        sim_test_load = new ServerMMOneSimulate(6, 3, 100000, srvr1, false);
        sim_test_load.run();
        //
        System.out.println();
        String srvr2 = "S";
        System.out.println("SRTN");
        System.out.println("Low Lambdas");
        sim_low_load = new ServerMMOneSimulate(.06, .03, 100000, srvr2, false);
        sim_low_load.run();
        System.out.println("Normal Lambda");
        sim_test_load = new ServerMMOneSimulate(6, 3, 100000, srvr2, false);
        sim_test_load.run();
        //
        System.out.println();
        String srvr3 = "R";
        System.out.println("RR");
        System.out.println("Low Lambdas");
        sim_low_load = new ServerMMOneSimulate(.06, .03, 100000, srvr3, false);
        sim_low_load.run();
        System.out.println("Normal Lambda");
        sim_test_load = new ServerMMOneSimulate(6, 3, 100000, srvr3, false);
        sim_test_load.run();
        //
        System.out.println();
        String srvr4 = "H";
        System.out.println("HSN");
        System.out.println("Low Lambdas");
        sim_low_load = new ServerMMOneSimulate(.06, .03, 100000, srvr4, false);
        sim_low_load.run();
        System.out.println("Normal Lambda");
        sim_test_load = new ServerMMOneSimulate(8, 3, 100000, srvr4, false);
        sim_test_load.run();
    }
}
