package serverpackage;

import java.util.List;
import serverpackage.eventpackage.Event;

public abstract class Server
{
    public Statistics stats;
    public abstract List<Event> arrival(Event event, double clock);
    public abstract Event departure(double clock);
    public abstract Event monitor(double clock, double max_time);
    public abstract Event cycle(double clock);
    public String getServerType(){
        return this.getClass().getSimpleName();
    }
}
