package nl.iobyte.achiever.generic.achievement.interfaces;

import nl.iobyte.achiever.generic.service.IService;
import java.util.UUID;

public interface IAchievementDataService extends IService {

    void handle(UUID uuid, String type_id, Object value);

}
