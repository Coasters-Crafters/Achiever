package nl.iobyte.achiever.generic.database;

import nl.iobyte.achiever.Achiever;
import nl.iobyte.achiever.generic.database.objects.AbstractDatabase;
import nl.iobyte.achiever.generic.database.objects.types.NullDatabase;
import nl.iobyte.dataapi.data.DataService;
import java.util.Collection;
import java.util.Map;
import java.util.Optional;

public class DatabaseService extends DataService<String, AbstractDatabase> implements IDatabaseService {

    private final AbstractDatabase empty = new NullDatabase();

    /**
     * Get Database
     * @param id String
     * @return Database
     */
    public AbstractDatabase getOrEmpty(String id) {
        return Optional.ofNullable(get(id)).orElse(empty);
    }

    public boolean execute(String id, String query, Map<Integer, Object> objects) {
        return getOrEmpty(id).execute(query, objects);
    }

    public void executeAsync(String id, String query, Map<Integer, Object> objects) {
        Achiever.getInstance().getScheduler().async(() -> execute(id, query, objects));
    }

    public int executeUpdate(String id, String query, Map<Integer, Object> objects) {
        return getOrEmpty(id).executeUpdate(query, objects);
    }

    public Collection<Map<String, Object>> executeQuery(String id, String query, Map<Integer, Object> objects) {
        return getOrEmpty(id).executeQuery(query, objects);
    }

    /**
     * Remove Database
     * @param id String
     */
    public AbstractDatabase remove(String id) {
        AbstractDatabase db = super.remove(id);
        if(db != null)
            try {
                db.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

        return db;
    }

    /**
     * Close database connections
     */
    public void stop() {
        for(AbstractDatabase database : getEntries().values())
            try {
                database.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
    }

}
