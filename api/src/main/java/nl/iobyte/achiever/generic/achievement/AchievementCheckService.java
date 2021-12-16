package nl.iobyte.achiever.generic.achievement;

import nl.iobyte.achiever.generic.achievement.interfaces.IAchievementCheck;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class AchievementCheckService extends ConcurrentHashMap<Class<?>, IAchievementCheck<?>> {

    /**
     * Perform check for type
     * @param clazz Class<T>
     * @param value T
     * @param expected T
     * @param <T> T
     * @return Boolean
     */
    public <T> boolean check(Class<T> clazz, T value, T expected) {
        return Optional.ofNullable(
                getAs(clazz)
        ).map(ac -> ac.check(
                value,
                expected
        )).orElse(false);
    }

    /**
     * Get check as type
     * @param clazz Class<T>
     * @param <T> T
     * @return IAchievementCheck<T>
     */
    @SuppressWarnings("unchecked")
    public <T> IAchievementCheck<T> getAs(Class<T> clazz) {
        try {
            return (IAchievementCheck<T>) get(clazz);
        } catch (Exception e) {
            return null;
        }
    }

}
