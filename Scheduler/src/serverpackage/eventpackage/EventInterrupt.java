package serverpackage.eventpackage;

public class EventInterrupt extends Event
{
    Event event_to_interrupt;
    public EventInterrupt(double clock, Event event_to_interrupt)
    {
        this.time_stamp = clock;
        this.event_to_interrupt;
    }
}
