package nl.iobyte.achiever.generic.achievement;

import nl.iobyte.achiever.generic.achievement.interfaces.IAchievement;
import nl.iobyte.achiever.generic.achievement.interfaces.IAchievementType;
import nl.iobyte.achiever.generic.service.IService;
import nl.iobyte.dataapi.data.DataService;
import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

public class AchievementService extends DataService<String, IAchievement<?>> implements IService {

    /**
     * {@inheritDoc}
     * @param entry IAchievement<?>
     * @return IAchievement<?>
     */
    public IAchievement<?> add(IAchievement<?> entry) {
        assert entry != null;

        //Add entry and register data listener if type exists
        return Optional.ofNullable(
                entry.getType()
        ).map(type -> {
            type.register();
            return super.add(entry);
        }).orElse(null);
    }

    /**
     * Get achievement as type
     * @param id String
     * @param <T> T
     * @return IAchievement<T>
     */
    @SuppressWarnings("unchecked")
    public <T> IAchievement<T> getAs(String id) {
        try {
            return (IAchievement<T>) get(id);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Get achievements with type
     * @param type IAchievementType<T>
     * @param <T> T
     * @return Collection<IAchievement<T>>
     */
    @SuppressWarnings("unchecked")
    public <T> Collection<IAchievement<T>> getWithType(IAchievementType<T> type) {
        assert type != null;

        return getEntries().values()
                .stream()
                .filter(a -> type.getID().equals(a.getTypeID()))
                .map(a -> {
                    try {
                        return (IAchievement<T>) a;
                    } catch (Exception e) {
                        return null;
                    }
                }).collect(Collectors.toList());
    }

}
