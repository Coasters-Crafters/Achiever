package nl.iobyte.achiever.generic.database;

import nl.iobyte.achiever.generic.service.IService;
import java.util.Collection;
import java.util.Map;

public interface IDatabaseService extends IService {

    /**
     * Execute
     * @param id String
     * @param query String
     * @param objects Map<Integer, Object>
     * @return Boolean
     */
    boolean execute(String id, String query, Map<Integer, Object> objects);

    /**
     * Execute Async
     * @param id String
     * @param query String
     * @param objects Map<Integer, Object>
     */
    void executeAsync(String id, String query, Map<Integer, Object> objects);

    /**
     * Execute Update
     * @param id String
     * @param query String
     * @param objects Map<Integer, Object>
     * @return Integer
     */
    int executeUpdate(String id, String query, Map<Integer, Object> objects);

    /**
     * Execute Query
     * @param id String
     * @param query String
     * @param objects Map<Integer, Object>
     * @return Collection<Map<String, Object>
     */
    Collection<Map<String, Object>> executeQuery(String id, String query, Map<Integer, Object> objects);

}
