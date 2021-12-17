package nl.iobyte.achiever.addons.vault.listeners;

import nl.iobyte.achiever.Achiever;
import nl.iobyte.achiever.addons.vault.data.UserEntry;
import nl.iobyte.achiever.addons.vault.data.UserVaultData;
import nl.iobyte.achiever.addons.vault.events.UserDepositEvent;
import nl.iobyte.achiever.generic.achievement.interfaces.IAchievementDataService;
import nl.iobyte.achiever.generic.database.IDatabaseService;
import nl.iobyte.dataapi.event.interfaces.IEventHandler;
import java.util.HashMap;

public class DepositListener implements IEventHandler<UserDepositEvent> {

    /**
     * {@inheritDoc}
     * @param event UserDepositEvent
     */
    public void fire(UserDepositEvent event) {
        UserEntry entry = Achiever.service(UserVaultData.class).getOrAdd(event.getUuid());

        //Handle achievements check
        Achiever.service(IAchievementDataService.class)
                .handle(
                        event.getUuid(),
                        "vault_deposit",
                        entry.deposit(event.getAmount())
                );

        //Update database
        Achiever.service(IDatabaseService.class).execute(
                "local",
                "INSERT OR REPLACE INTO vault(uuid, deposit, withdraw) VALUES (?,?,?)",
                new HashMap<>(){{
                    put(1, event.getUuid().toString());
                    put(2, entry.getDeposit());
                    put(3, entry.getWithdraw());
                }}
        );
    }

}
