package nl.iobyte.achiever.generic.achievement.interfaces;

import nl.iobyte.dataapi.data.interfaces.IData;
import java.util.Collection;

public interface IAchievementType<T> extends IData<String> {

    /**
     * Data type
     * @return Class<T>
     */
    Class<T> getType();

    /**
     * Get check for data type
     * @return IAchievementCheck<T>
     */
    IAchievementCheck<T> getCheck();

    /**
     * Register data tracker
     */
    void register();

    /**
     * Amount of achievements using type
     * @return Integer
     */
    int usages();

    /**
     * Get achievements using type
     * @return Collection<IAchievement<T>>
     */
    Collection<IAchievement<T>> getAchievements();

}
