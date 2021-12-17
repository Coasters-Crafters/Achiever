package nl.iobyte.achiever.addons.vault.data;

import com.google.common.util.concurrent.AtomicDouble;
import nl.iobyte.dataapi.data.objects.Data;
import java.util.UUID;

public class UserEntry extends Data<UUID> {

    private final AtomicDouble deposit, withdraw;

    public UserEntry(UUID uuid, double deposit, double withdraw) {
       super(uuid);
        this.deposit = new AtomicDouble(deposit);
        this.withdraw = new AtomicDouble(withdraw);
    }

    /**
     * Get deposited amount
     * @return Double
     */
    public double getDeposit() {
        return deposit.get();
    }

    /**
     * Get withdrawn amount
     * @return Double
     */
    public double getWithdraw() {
        return deposit.get();
    }

    /**
     * Deposit and get new amount
     * @param amount Double
     * @return Double
     */
    public double deposit(double amount) {
        return deposit.addAndGet(amount);
    }

    /**
     * Withdraw and get new amount
     * @param amount Double
     * @return Double
     */
    public double withdraw(double amount) {
        return withdraw.addAndGet(amount);
    }

}
