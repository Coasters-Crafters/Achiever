package nl.iobyte.achiever.generic.achievement.objects.checks;

import nl.iobyte.achiever.generic.achievement.interfaces.IAchievementCheck;

public class AchievementStringCheck implements IAchievementCheck<String> {

    /**
     * {@inheritDoc}
     * @param value String
     * @param expected String
     * @return Boolean
     */
    public boolean check(String value, String expected) {
        return expected.equals(value);
    }

}
