package nl.iobyte.achiever.generic.events;

import nl.iobyte.achiever.generic.service.IService;
import nl.iobyte.dataapi.event.interfaces.IEvent;
import nl.iobyte.dataapi.event.interfaces.IEventHandler;

public interface IEventService extends IService {

    /**
     * Register event
     * @param type Class<T>
     * @param handler IEventHandler<T>
     * @param <T> extends IEvent
     */
    <T extends IEvent> IEventService on(Class<T> type, IEventHandler<T> handler);

    /**
     * Fire event
     * @param event IEvent
     */
    void fire(IEvent event);

}
