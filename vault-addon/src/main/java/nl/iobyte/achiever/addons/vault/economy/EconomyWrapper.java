package nl.iobyte.achiever.addons.vault.economy;

import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.economy.EconomyResponse;
import nl.iobyte.achiever.Achiever;
import nl.iobyte.achiever.addons.vault.events.UserDepositEvent;
import nl.iobyte.achiever.addons.vault.events.UserWithdrawEvent;
import nl.iobyte.achiever.generic.events.IEventService;
import nl.iobyte.dataapi.event.interfaces.IEvent;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import java.util.List;

/**
 * Wrapper around Economy implementation to fire events.
 * @author Rsl1122
 */
public class EconomyWrapper implements Economy {

    private final Economy original;

    public EconomyWrapper(Economy original) {
        this.original = original;
    }

    private void callEvent(IEvent event) {
        Achiever.service(IEventService.class).fire(event);
    }

    public boolean isEnabled() {
        return original.isEnabled();
    }

    public String getName() {
        return original.getName();
    }

    public boolean hasBankSupport() {
        return original.hasBankSupport();
    }

    public int fractionalDigits() {
        return original.fractionalDigits();
    }

    public String format(double amount) {
        return original.format(amount);
    }

    public String currencyNamePlural() {
        return original.currencyNamePlural();
    }

    public String currencyNameSingular() {
        return original.currencyNameSingular();
    }

    @Deprecated
    public boolean hasAccount(String s) {
        return original.hasAccount(s);
    }

    public boolean hasAccount(OfflinePlayer player) {
        return original.hasAccount(player);
    }

    @Deprecated
    public boolean hasAccount(String s, String s1) {
        return original.hasAccount(s, s1);
    }

    public boolean hasAccount(OfflinePlayer player, String s) {
        return original.hasAccount(player, s);
    }

    @Deprecated
    public double getBalance(String s) {
        return original.getBalance(s);
    }

    public double getBalance(OfflinePlayer player) {
        return original.getBalance(player);
    }

    @Deprecated
    public double getBalance(String s, String s1) {
        return original.getBalance(s, s1);
    }

    public double getBalance(OfflinePlayer player, String s) {
        return original.getBalance(player, s);
    }

    @Deprecated
    public boolean has(String s, double amount) {
        return original.has(s, amount);
    }

    public boolean has(OfflinePlayer player, double amount) {
        return original.has(player, amount);
    }

    @Deprecated
    public boolean has(String s, String s1, double amount) {
        return original.has(s, s1, amount);
    }

    public boolean has(OfflinePlayer player, String s, double amount) {
        return original.has(player, s, amount);
    }

    @Deprecated
    public EconomyResponse withdrawPlayer(String s, double amount) {
        return withdrawPlayer(Bukkit.getOfflinePlayer(s), amount);
    }

    public EconomyResponse withdrawPlayer(OfflinePlayer player, double amount) {
        EconomyResponse response = original.withdrawPlayer(player, amount);
        if(response != null && response.transactionSuccess())
            callEvent(new UserWithdrawEvent(player.getUniqueId(), amount));

        return response;
    }

    @Deprecated
    public EconomyResponse withdrawPlayer(String s, String s1, double amount) {
        return withdrawPlayer(Bukkit.getOfflinePlayer(s), s1, amount);
    }

    public EconomyResponse withdrawPlayer(OfflinePlayer player, String s, double amount) {
        EconomyResponse response = original.withdrawPlayer(player, s, amount);
        if(response != null && response.transactionSuccess())
            callEvent(new UserWithdrawEvent(player.getUniqueId(), amount));

        return response;
    }

    @Deprecated
    public EconomyResponse depositPlayer(String s, double amount) {
        return depositPlayer(Bukkit.getOfflinePlayer(s), amount);
    }

    public EconomyResponse depositPlayer(OfflinePlayer player, double amount) {
        EconomyResponse response = original.depositPlayer(player, amount);
        if(response != null && response.transactionSuccess())
            callEvent(new UserDepositEvent(player.getUniqueId(), amount));

        return response;
    }

    @Deprecated
    public EconomyResponse depositPlayer(String s, String s1, double amount) {
        return depositPlayer(Bukkit.getOfflinePlayer(s), s1, amount);
    }

    public EconomyResponse depositPlayer(OfflinePlayer player, String s, double amount) {
        EconomyResponse response = original.depositPlayer(player, s, amount);
        if(response != null && response.transactionSuccess())
            callEvent(new UserDepositEvent(player.getUniqueId(), amount));

        return response;
    }

    @Deprecated
    public EconomyResponse createBank(String s, String s1) {
        return original.createBank(s, Bukkit.getOfflinePlayer(s1));
    }

    public EconomyResponse createBank(String bankName, OfflinePlayer player) {
        return original.createBank(bankName, player);
    }

    public EconomyResponse deleteBank(String s) {
        return original.deleteBank(s);
    }

    public EconomyResponse bankBalance(String s) {
        return original.bankBalance(s);
    }

    public EconomyResponse bankHas(String s, double amount) {
        return original.bankHas(s, amount);
    }

    public EconomyResponse bankWithdraw(String s, double amount) {
        return original.bankWithdraw(s, amount);
    }

    public EconomyResponse bankDeposit(String s, double amount) {
        return original.bankDeposit(s, amount);
    }

    @Deprecated
    public EconomyResponse isBankOwner(String s, String s1) {
        return original.isBankOwner(s, s1);
    }

    public EconomyResponse isBankOwner(String s, OfflinePlayer player) {
        return original.isBankOwner(s, player);
    }

    @Deprecated
    public EconomyResponse isBankMember(String s, String s1) {
        return original.isBankMember(s, s1);
    }

    public EconomyResponse isBankMember(String s, OfflinePlayer player) {
        return original.isBankMember(s, player);
    }

    public List<String> getBanks() {
        return original.getBanks();
    }

    @Deprecated
    public boolean createPlayerAccount(String s) {
        return original.createPlayerAccount(s);
    }

    public boolean createPlayerAccount(OfflinePlayer player) {
        return original.createPlayerAccount(player);
    }

    @Deprecated
    public boolean createPlayerAccount(String s, String s1) {
        return original.createPlayerAccount(Bukkit.getOfflinePlayer(s), s1);
    }

    public boolean createPlayerAccount(OfflinePlayer player, String world) {
        return original.createPlayerAccount(player, world);
    }
}