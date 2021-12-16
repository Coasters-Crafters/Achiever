package nl.iobyte.achiever.spigot;

import nl.iobyte.achiever.Achiever;
import nl.iobyte.achiever.generic.IAchieverInvoker;
import nl.iobyte.achiever.generic.achievement.AchievementDataService;
import nl.iobyte.achiever.generic.achievement.interfaces.IAchievementDataService;
import nl.iobyte.achiever.generic.events.EventService;
import nl.iobyte.achiever.generic.events.IEventService;
import nl.iobyte.achiever.generic.logging.ILogging;
import nl.iobyte.achiever.generic.scheduler.IScheduler;
import nl.iobyte.achiever.spigot.logging.SpigotLogging;
import nl.iobyte.achiever.spigot.scheduler.SpigotScheduler;
import org.bukkit.plugin.java.JavaPlugin;

public class SpigotAchieverInvoker extends JavaPlugin implements IAchieverInvoker {

    private ILogging logging;
    private IScheduler scheduler;

    public void onEnable() {
        logging = new SpigotLogging(this);
        scheduler = new SpigotScheduler(this);

        //Initialize plugin
        Achiever achiever = new Achiever(this);

        ///Register services
        achiever.register(
                IEventService.class,
                EventService.class
        );
        achiever.register(
                IAchievementDataService.class,
                AchievementDataService.class
        );

        //Start plugin
        achiever.start();
    }

    public void onDisable() {
        Achiever.getInstance().stop();
    }

    /**
     * {@inheritDoc}
     * @return SpigotLogging
     */
    public ILogging getLogging() {
        return logging;
    }

    /**
     * {@inheritDoc}
     * @return SpigotScheduler
     */
    public IScheduler getScheduler() {
        return scheduler;
    }

}
