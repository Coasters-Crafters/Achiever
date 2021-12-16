package nl.iobyte.achiever.generic.database.objects.types;

import nl.iobyte.achiever.generic.database.objects.AbstractDatabase;
import java.sql.Connection;
import java.util.Collection;
import java.util.Map;

public class NullDatabase extends AbstractDatabase {

    public NullDatabase() {
        super("empty");
    }

    @Override
    public Connection getConnection() {
        return null;
    }

    @Override
    public void close() {}

    @Override
    public boolean execute(String query, Map<Integer, Object> objects) {
        return false;
    }

    @Override
    public int executeUpdate(String query, Map<Integer, Object> objects) {
        return 0;
    }

    @Override
    public Collection<Map<String, Object>> executeQuery(String query, Map<Integer, Object> objects) {
        return null;
    }

}