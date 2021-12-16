package nl.iobyte.achiever.generic.achievement.objects;

import nl.iobyte.achiever.generic.achievement.interfaces.IAchievement;
import nl.iobyte.achiever.generic.achievement.interfaces.IAchievementCheck;
import nl.iobyte.achiever.generic.achievement.interfaces.IAchievementType;
import nl.iobyte.dataapi.data.objects.Data;
import java.util.Collection;
import java.util.concurrent.atomic.AtomicInteger;

public abstract class AbstractAchievementType<T> extends Data<String> implements IAchievementType<T> {

    private final Class<T> type;
    private final AtomicInteger usages = new AtomicInteger(0);

    public AbstractAchievementType(String id, Class<T> type) {
        super(id);
        this.type = type;
    }

    /**
     * {@inheritDoc}
     * @return Class<T>
     */
    public Class<T> getType() {
        return type;
    }

    /**
     * {@inheritDoc}
     * @return IAchievementCheck<T>
     */
    public IAchievementCheck<T> getCheck() {
        return null;//TODO Service
    }

    /**
     * {@inheritDoc}
     */
    public void register() {
        //Check if already registered
        if(usages.incrementAndGet() != 1)
            return;

        //Load
        load();
    }

    /**
     * Load data listener for type
     */
    public abstract void load();

    /**
     * {@inheritDoc}
     * @return Integer
     */
    public int usages() {
        return usages.get();
    }

    /**
     * {@inheritDoc}
     * @return Collection<IAchievement<T>>
     */
    public Collection<IAchievement<T>> getAchievements() {
        return null;//TODO service
    }

}
