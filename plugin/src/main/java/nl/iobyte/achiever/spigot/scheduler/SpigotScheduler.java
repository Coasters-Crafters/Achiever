package nl.iobyte.achiever.spigot.scheduler;

import nl.iobyte.achiever.generic.scheduler.IScheduler;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

public class SpigotScheduler implements IScheduler {

    private final Plugin plugin;

    public SpigotScheduler(Plugin plugin) {
        this.plugin = plugin;
    }

    /**
     * {@inheritDoc}
     * @param r Runnable
     */
    public void sync(Runnable r) {
        Bukkit.getScheduler().runTask(plugin, r);
    }

    /**
     * {@inheritDoc}
     * @param r Runnable
     */
    public void async(Runnable r) {
        Bukkit.getScheduler().runTaskAsynchronously(plugin, r);
    }

}
