package nl.iobyte.achiever.generic.achievement.objects.checks;

import nl.iobyte.achiever.generic.achievement.interfaces.IAchievementCheck;

public class AchievementDoubleCheck implements IAchievementCheck<Double> {

    /**
     * {@inheritDoc}
     * @param value Double
     * @param expected Double
     * @return Boolean
     */
    public boolean check(Double value, Double expected) {
        return value >= expected;
    }

}
