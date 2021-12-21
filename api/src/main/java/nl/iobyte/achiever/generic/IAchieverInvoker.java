package nl.iobyte.achiever.generic;

import nl.iobyte.achiever.generic.logging.ILogging;
import nl.iobyte.achiever.generic.scheduler.IScheduler;
import java.io.File;

public interface IAchieverInvoker {

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

    /**
     * Get data folder of invoker
     * @return File
     */
    File getDataFolder();

}
