package nl.iobyte.achiever.addons.vault.events;

import nl.iobyte.dataapi.event.interfaces.IEvent;
import java.util.UUID;

public class UserWithdrawEvent implements IEvent {

    private final UUID uuid;
    private final double amount;

    public UserWithdrawEvent(UUID uuid, double amount) {
        this.uuid = uuid;
        this.amount = amount;
    }

    public UUID getUuid() {
        return uuid;
    }

    public double getAmount() {
        return amount;
    }

}
