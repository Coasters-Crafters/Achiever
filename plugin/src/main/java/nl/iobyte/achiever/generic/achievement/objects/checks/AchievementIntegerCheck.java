package nl.iobyte.achiever.generic.achievement.objects.checks;

import nl.iobyte.achiever.generic.achievement.interfaces.IAchievementCheck;

public class AchievementIntegerCheck implements IAchievementCheck<Integer> {

    /**
     * {@inheritDoc}
     * @param value Integer
     * @param expected Integer
     * @return Boolean
     */
    public boolean check(Integer value, Integer expected) {
        return value >= expected;
    }

}
