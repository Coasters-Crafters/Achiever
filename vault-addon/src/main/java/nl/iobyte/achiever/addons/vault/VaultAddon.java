package nl.iobyte.achiever.addons.vault;

import net.milkbowl.vault.economy.Economy;
import nl.iobyte.achiever.Achiever;
import nl.iobyte.achiever.addons.vault.data.UserVaultData;
import nl.iobyte.achiever.addons.vault.economy.EconomyWrapper;
import nl.iobyte.achiever.addons.vault.events.UserDepositEvent;
import nl.iobyte.achiever.addons.vault.events.UserWithdrawEvent;
import nl.iobyte.achiever.addons.vault.listeners.DepositListener;
import nl.iobyte.achiever.addons.vault.listeners.WithdrawListener;
import nl.iobyte.achiever.generic.achievement.AchievementTypeService;
import nl.iobyte.achiever.generic.events.IEventService;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.ServicePriority;
import org.bukkit.plugin.ServicesManager;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;

public class VaultAddon {

    private static VaultAddon instance;
    private final Plugin plugin;
    private final AtomicBoolean loaded = new AtomicBoolean(false);

    public VaultAddon(Plugin plugin) {
        instance = this;
        this.plugin = plugin;

        AchievementTypeService service = Achiever.service(AchievementTypeService.class);
        service.add(new VaultAchievementType("deposit"));
        service.add(new VaultAchievementType("withdraw"));
    }

    public static VaultAddon getInstance() {
        return instance;
    }

    /**
     * Register stuff
     */
    public void load() {
        if(!loaded.compareAndSet(false, true))
            return;

        if(!Bukkit.getPluginManager().isPluginEnabled("Vault")) {
            Achiever.getInstance().getLogging().error("Unable to listen to balance changes due to no Vault dependency found");
            return;
        }

        //Register data service
        Achiever.getInstance().register(UserVaultData.class);

        //Register events
        IEventService service = Achiever.service(IEventService.class);
        service.on(UserDepositEvent.class, new DepositListener());
        service.on(UserWithdrawEvent.class, new WithdrawListener());

        //Economy
        Optional<Economy> economy = getEconomy();
        if (economy.isPresent()) {
            registerWrapper(economy.get());
        } else {
            tryAgainOnStart();
        }
    }

    /**
     * Try again after done loading
     */
    private void tryAgainOnStart() {
        // Run when server has done loading
        Achiever.getInstance()
                .getScheduler()
                .sync(
                        () -> getEconomy().ifPresent(
                                this::registerWrapper
                        )
                );
    }

    /**
     * Get registered economy
     * @return Optional<Economy>
     */
    private Optional<Economy> getEconomy() {
        ServicesManager services = Bukkit.getServer().getServicesManager();
        RegisteredServiceProvider<Economy> economyService = services.getRegistration(Economy.class);
        if (economyService == null)
            return Optional.empty();

        return Optional.of(
                economyService.getProvider()
        );
    }

    /**
     * Wrap original economy
     * @param original Economy
     */
    private void registerWrapper(Economy original) {
        Economy wrappedEco = original instanceof EconomyWrapper ? original : new EconomyWrapper(original);
        Bukkit.getServer().getServicesManager().register(Economy.class, wrappedEco, plugin, ServicePriority.Highest);
        Achiever.getInstance().getLogging().info("Events for vault registered - can track spend/earned money.");
    }

}
