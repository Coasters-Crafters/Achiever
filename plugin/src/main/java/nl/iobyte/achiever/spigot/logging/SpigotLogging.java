package nl.iobyte.achiever.spigot.logging;

import nl.iobyte.achiever.generic.logging.ILogging;
import org.bukkit.plugin.Plugin;

public class SpigotLogging implements ILogging {

    private final Plugin plugin;

    public SpigotLogging(Plugin plugin) {
        this.plugin = plugin;
    }

    /**
     * {@inheritDoc}
     * @param message String
     */
    public void info(String message) {
        plugin.getLogger().info(message);
    }

    /**
     * {@inheritDoc}
     * @param message String
     */
    public void error(String message) {
        plugin.getLogger().severe(message);
    }

}
