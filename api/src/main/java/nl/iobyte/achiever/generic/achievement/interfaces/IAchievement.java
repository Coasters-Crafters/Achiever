package nl.iobyte.achiever.generic.achievement.interfaces;

import nl.iobyte.dataapi.data.interfaces.IData;

public interface IAchievement<T> extends IData<String> {

    /**
     * Get type identifier
     * @return String
     */
    String getTypeID();

    /**
     * Get type
     * @return IAchievementType<T>
     */
    IAchievementType<T> getType();

    /**
     * Get expected value
     * @return T
     */
    T getExpected();

}
