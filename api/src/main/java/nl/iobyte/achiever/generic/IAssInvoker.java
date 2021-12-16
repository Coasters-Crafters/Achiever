package nl.iobyte.achiever.generic;

import nl.iobyte.achiever.generic.logging.ILogging;
import nl.iobyte.achiever.generic.scheduler.IScheduler;

public interface IAssInvoker {

    /**
     * Get logging implementation
     * @return ILogging
     */
    ILogging getLogging();

    /**
     * Get scheduler implementation
     * @return IScheduler
     */
    IScheduler getScheduler();

}
