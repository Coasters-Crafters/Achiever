package nl.iobyte.achiever.generic.events;

import nl.iobyte.achiever.generic.achievement.interfaces.IAchievement;
import nl.iobyte.dataapi.event.interfaces.IEvent;
import java.util.UUID;

public class AchieveEvent implements IEvent {

    private final IAchievement<?> achievement;
    private final UUID uuid;
    private final Object value;

    public AchieveEvent(IAchievement<?> achievement, UUID uuid, Object value) {
        this.achievement = achievement;
        this.uuid = uuid;
        this.value = value;
    }

    /**
     * Get achievement
     * @return IAchievement<?>
     */
    public IAchievement<?> getAchievement() {
        return achievement;
    }

    /**
     * Get player's uuid
     * @return UUID
     */
    public UUID getUuid() {
        return uuid;
    }

    /**
     * Get value that passed
     * @return Object
     */
    public Object getValue() {
        return value;
    }

}
