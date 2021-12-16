package nl.iobyte.achiever.generic.achievement;

import nl.iobyte.achiever.Achiever;
import nl.iobyte.achiever.generic.achievement.interfaces.IAchievement;
import nl.iobyte.achiever.generic.achievement.interfaces.IAchievementCheck;
import nl.iobyte.achiever.generic.achievement.interfaces.IAchievementDataService;
import nl.iobyte.achiever.generic.achievement.interfaces.IAchievementType;
import nl.iobyte.achiever.generic.events.AchieveEvent;
import nl.iobyte.achiever.generic.events.IEventService;
import nl.iobyte.dataapi.data.DataService;
import java.util.Collection;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class AchievementDataService extends DataService<UUID, AchievementUserData> implements IAchievementDataService {

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
            //Get type
            IAchievementType<?> type = Achiever.service(AchievementTypeService.class).get(type_id);
            if(type == null) {
                Achiever.getInstance().getLogging().error("Unable to find achievement type: "+type_id);
                return;
            }

            //Check if passed value can be used with type
            if(!type.getType().isInstance(value) && !type.getType().isAssignableFrom(value.getClass())) {
                Achiever.getInstance().getLogging().error("Value of type: "+value.getClass().getSimpleName()+" doesn't match with achievement type of: "+type.getType().getSimpleName());
                return;
            }

            //Get check for type
            IAchievementCheck<?> check = type.getCheck();
            if(check == null) {
                Achiever.getInstance().getLogging().error("Unable to find achievement check for type: "+type.getType().getSimpleName());
                return;
            }

            //Get achievements for type
            Collection<? extends IAchievement<?>> achievements = type.getAchievements();

            //Filter achievements
            AchievementUserData data = get(uuid);
            if(data != null)
                achievements = achievements.stream()
                        .filter(a -> !data.hasAchieved(a.getID()))
                        .collect(Collectors.toList());

            if(achievements.isEmpty())
                return;

            //Check achievements
            achievements.forEach(a -> {
                Boolean b = check.checkRaw(value, a.getExpected());
                if(b == null) {
                    Achiever.getInstance().getLogging().error("Check for achievement type: "+type.getType().getSimpleName()+" failed to cast parameters");
                    return;
                }

                if(!b)
                    return;

                //Set achieved
                if(data != null) {
                    data.achieve(a.getID());
                } else {
                    add(new AchievementUserData(
                            uuid,
                            new ConcurrentHashMap<>(){{
                                put(a.getID(), true);
                            }}
                    ));
                }

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
