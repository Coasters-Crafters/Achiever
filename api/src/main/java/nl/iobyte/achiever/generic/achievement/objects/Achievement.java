package nl.iobyte.achiever.generic.achievement.objects;

import nl.iobyte.achiever.generic.achievement.interfaces.IAchievement;
import nl.iobyte.achiever.generic.achievement.interfaces.IAchievementType;
import nl.iobyte.dataapi.data.objects.Data;

public class Achievement<T> extends Data<String> implements IAchievement<T> {

    private final String type_id;
    private final T expected;

    public Achievement(String id, String type_id, T expected) {
        super(id);
        this.type_id = type_id;
        this.expected = expected;
    }

    /**
     * {@inheritDoc}
     * @return String
     */
    public String getTypeID() {
        return type_id;
    }

    /**
     * {@inheritDoc}
     * @return IAchievementType<T>
     */
    public IAchievementType<T> getType() {
        return null;//TODO Service
    }

    /**
     * {@inheritDoc}
     * @return T
     */
    public T getExpected() {
        return expected;
    }

}
