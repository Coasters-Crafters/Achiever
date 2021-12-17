package nl.iobyte.achiever.generic.achievement.interfaces;

import nl.iobyte.achiever.generic.service.IService;
import java.util.UUID;

public interface IAchievementDataService extends IService {

    /**
     * Check if user has achieved achievement
     * @param uuid UUID
     * @param id String
     * @return Boolean
     */
    boolean hasAchieved(UUID uuid, String id);

    /**
     * Handle value change for achievement type
     * @param uuid UUID
     * @param type_id String
     * @param value Object
     */
    void handle(UUID uuid, String type_id, Object value);

}
