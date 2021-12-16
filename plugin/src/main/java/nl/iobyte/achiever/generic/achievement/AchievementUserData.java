package nl.iobyte.achiever.generic.achievement;

import nl.iobyte.dataapi.data.objects.Data;
import java.util.Map;
import java.util.UUID;

public class AchievementUserData extends Data<UUID> {

    private final Map<String, Boolean> data;

    public AchievementUserData(UUID id, Map<String, Boolean> data) {
        super(id);
        this.data = data;
    }

    /**
     * Check if user has achieved
     * @param id String
     * @return Boolean
     */
    public boolean hasAchieved(String id) {
        return data.getOrDefault(id, false);
    }

    public void achieve(String id) {
        data.put(id, true);
    }

}
