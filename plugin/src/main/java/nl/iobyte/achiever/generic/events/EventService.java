package nl.iobyte.achiever.generic.events;

import nl.iobyte.achiever.Achiever;
import nl.iobyte.dataapi.event.EventDriver;
import nl.iobyte.dataapi.event.interfaces.IEvent;
import nl.iobyte.dataapi.event.interfaces.IEventHandler;

public class EventService implements IEventService {

    private final EventDriver driver = new EventDriver();

    /**
     * {@inheritDoc}
     * @param type Class<T>
     * @param handler IEventHandler<T>
     * @param <T> extends IEvent
     */
    public <T extends IEvent> IEventService on(Class<T> type, IEventHandler<T> handler) {
        driver.on(type, handler);
        return this;
    }

    /**
     * {@inheritDoc}
     * @param event IEvent
     */
    public void fire(IEvent event) {
        Achiever.getInstance().getScheduler().async(() -> driver.fire(event));
    }

}
