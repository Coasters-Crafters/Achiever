package nl.iobyte.achiever.generic.scheduler;

public interface IScheduler {

    /**
     * Run sync
     * @param r Runnable
     */
    void sync(Runnable r);

    /**
     * Run async
     * @param r Runnable
     */
    void async(Runnable r);

}
