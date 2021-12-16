package nl.iobyte.achiever.generic.achievement.interfaces;

public interface IAchievementCheck<T> {

    /**
     * Check if value passes expected
     * @param value T
     * @param expected T
     * @return Boolean
     */
    boolean check(T value, T expected);

    /**
     * Cast raw, then check
     * @param value Object
     * @param expected Object
     * @return Boolean
     */
    @SuppressWarnings("unchecked")
    default Boolean checkRaw(Object value, Object expected) {
        T val, exp;
        try {
            val = (T) value;
            exp = (T) expected;
        } catch (Exception e) {
            return null;
        }

        return check(val, exp);
    }

}
