package nl.iobyte.achiever.generic.achievement.interfaces;

import nl.iobyte.achiever.generic.service.IService;
import java.util.UUID;

public interface IAchievementDataService extends IService {

    /**
     * Handle value change for achievement type
     * @param uuid UUID
     * @param type_id String
     * @param value Object
     */
    void handle(UUID uuid, String type_id, Object value);

}
