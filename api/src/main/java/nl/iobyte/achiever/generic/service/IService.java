package nl.iobyte.achiever.generic.service;

public interface IService {

    /**
     * Called on service start
     */
    default void start() {}

    /**
     * Called on service stop
     */
    default void stop() {}

}
