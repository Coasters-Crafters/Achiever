package nl.iobyte.achiever.generic.achievement;

import nl.iobyte.achiever.Achiever;
import nl.iobyte.achiever.generic.achievement.interfaces.IAchievement;
import nl.iobyte.achiever.generic.achievement.interfaces.IAchievementCheck;
import nl.iobyte.achiever.generic.achievement.interfaces.IAchievementDataService;
import nl.iobyte.achiever.generic.achievement.interfaces.IAchievementType;
import nl.iobyte.achiever.generic.events.AchieveEvent;
import nl.iobyte.achiever.generic.events.IEventService;
import java.util.Collection;
import java.util.UUID;

public class AchievementDataService implements IAchievementDataService {

    /**
     * {@inheritDoc}
     * @param uuid UUID
     * @param type_id String
     * @param value Object
     */
    public void handle(UUID uuid, String type_id, Object value) {
        assert uuid != null;
        assert type_id != null;
        assert value != null;
        Achiever.getInstance().getScheduler().async(() -> {
            IAchievementType<?> type = Achiever.service(AchievementTypeService.class).get(type_id);
            if(type == null) {
                Achiever.getInstance().getLogging().error("Unable to find achievement type: "+type_id);
                return;
            }

            if(!type.getType().isInstance(value) && !type.getType().isAssignableFrom(value.getClass())) {
                Achiever.getInstance().getLogging().error("Value of type: "+value.getClass().getSimpleName()+" doesn't match with achievement type of: "+type.getType().getSimpleName());
                return;
            }

            IAchievementCheck<?> check = type.getCheck();
            if(check == null) {
                Achiever.getInstance().getLogging().error("Unable to find achievement check for type: "+type.getType().getSimpleName());
                return;
            }

            Collection<? extends IAchievement<?>> achievements = type.getAchievements();
            //TODO filter achievements that don't need to be checked

            //Check achievements
            achievements.forEach(a -> {
                Boolean b = check.checkRaw(value, a.getExpected());
                if(b == null) {
                    Achiever.getInstance().getLogging().error("Check for achievement type: "+type.getType().getSimpleName()+" failed to cast parameters");
                    return;
                }

                if(!b)
                    return;

                //Fire event
                Achiever.service(
                        IEventService.class
                ).fire(new AchieveEvent(
                        a,
                        uuid,
                        value
                ));
            });
        });
    }

}
