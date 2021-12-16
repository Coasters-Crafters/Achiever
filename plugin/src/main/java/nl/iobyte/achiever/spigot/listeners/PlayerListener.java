package nl.iobyte.achiever.spigot.listeners;

import nl.iobyte.achiever.Achiever;
import nl.iobyte.achiever.generic.achievement.AchievementDataService;
import nl.iobyte.achiever.generic.achievement.AchievementUserData;
import nl.iobyte.achiever.generic.achievement.interfaces.IAchievementDataService;
import nl.iobyte.achiever.generic.database.DatabaseService;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class PlayerListener implements Listener {

    //Load player data
    public void onJoin(PlayerJoinEvent e) {
        Achiever.getInstance().getScheduler().async(() -> {
            Collection<Map<String,Object>> rows = Achiever.service(DatabaseService.class).executeQuery(
                    "local",
                    "SELECT id FROM achieved WHERE uuid=?",//TODO Create table and save data into table
                    new HashMap<>(){{
                        put(1, e.getPlayer().getUniqueId().toString());
                    }}
            );
            if(rows == null || rows.isEmpty())
                return;

            Map<String,Boolean> m = new ConcurrentHashMap<>();
            for(Map<String,Object> row : rows) {
                m.put(
                        (String) row.get("id"),
                        true
                );
            }

            //Register user data
            AchievementDataService service = (AchievementDataService) Achiever.service(IAchievementDataService.class);
            service.add(new AchievementUserData(
                    e.getPlayer().getUniqueId(),
                    m
            ));
        });
    }

    //Remove player data
    public void onLeave(PlayerQuitEvent e) {
        AchievementDataService service = (AchievementDataService) Achiever.service(IAchievementDataService.class);
        service.remove(e.getPlayer().getUniqueId());
    }

}
