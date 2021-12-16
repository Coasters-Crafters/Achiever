package nl.iobyte.achiever.generic.achievement.interfaces;

public interface IAchievementCheck<T> {

    /**
     * Check if value passes expected
     * @param value T
     * @param expected T
     * @return Boolean
     */
    boolean check(T value, T expected);

}
