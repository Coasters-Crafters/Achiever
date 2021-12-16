package nl.iobyte.achiever.spigot.listeners;

import nl.iobyte.achiever.Achiever;
import nl.iobyte.achiever.generic.database.DatabaseService;
import nl.iobyte.achiever.generic.events.AchieveEvent;
import nl.iobyte.dataapi.event.interfaces.IEventHandler;
import java.util.HashMap;

public class AchievementListener implements IEventHandler<AchieveEvent> {

    /**
     * {@inheritDoc}
     * @param event AchieveEvent
     */
    public void fire(AchieveEvent event) {
        Achiever.service(DatabaseService.class).execute(
                "local",
                "INSERT IGNORE INTO achieved(id, uuid) VALUES (?,?)",
                new HashMap<>(){{
                    put(1, event.getAchievement().getID());
                    put(2, event.getUuid().toString());
                }}
        );
    }

}
