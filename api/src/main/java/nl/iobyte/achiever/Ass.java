package nl.iobyte.achiever;

import nl.iobyte.achiever.generic.IAssInvoker;
import nl.iobyte.achiever.generic.logging.ILogging;
import nl.iobyte.achiever.generic.registry.ImplementationRegistry;
import nl.iobyte.achiever.generic.scheduler.IScheduler;
import nl.iobyte.achiever.generic.service.IService;

public class Ass extends ImplementationRegistry<IService> implements IAssInvoker {

    private static Ass instance;
    private final IAssInvoker invoker;

    public Ass(IAssInvoker invoker) {
        instance = this;
        this.invoker = invoker;

        //Register
    }

    /**
     * Start services
     */
    public void start() {
        getValues().forEach(service -> {
            getLogging().info("Starting service ["+service.getClass().getSimpleName()+"]...");
            service.start();
        });
    }

    /**
     * Stop services
     */
    public void stop() {
        getValues().forEach(service -> {
            getLogging().info("Starting service ["+service.getClass().getSimpleName()+"]...");
            service.stop();
        });
    }

    /**
     * Get service instance from class
     * @param clazz Class<T>
     * @param <T> extends IService
     * @return T
     */
    public static <T extends IService> T service(Class<T> clazz) {
        return getInstance().get(clazz);
    }

    /**
     * Get plugin instance
     * @return Ass
     */
    public static Ass getInstance() {
        return instance;
    }

    /**
     * {@inheritDoc}
     * @return ILogging
     */
    public ILogging getLogging() {
        return invoker.getLogging();
    }

    /**
     * {@inheritDoc}
     * @return IScheduler
     */
    public IScheduler getScheduler() {
        return invoker.getScheduler();
    }

}
