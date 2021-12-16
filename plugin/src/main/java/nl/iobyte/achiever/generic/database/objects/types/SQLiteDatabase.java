package nl.iobyte.achiever.generic.database.objects.types;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import nl.iobyte.achiever.generic.database.objects.AbstractDatabase;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class SQLiteDatabase extends AbstractDatabase {

    private HikariDataSource source;

    public SQLiteDatabase(String id, File file) {
        super(id);

        //File
        if (!file.exists()) {
            if(!file.getParentFile().exists())
                if(!file.getParentFile().mkdirs())
                    return;

            try {
                if (!file.createNewFile())
                    return;
            } catch (IOException e) {
                e.printStackTrace();
                return;
            }
        }

        //Config
        HikariConfig config = new HikariConfig();
        config.setPoolName("navigator");
        config.setUsername(null);
        config.setPassword(null);
        config.setDriverClassName("org.sqlite.JDBC");
        config.setConnectionTestQuery("SELECT 1");
        config.setMaximumPoolSize(5);

        Properties prop = new Properties();
        prop.setProperty("date_string_format", "yyyy-MM-dd HH:mm:ss");

        config.setJdbcUrl("jdbc:sqlite:" + file.getAbsolutePath());
        config.setDataSourceProperties(prop);
        source = new HikariDataSource(config);
    }

    /**
     * {@inheritDoc}
     * @return Connection
     * @throws SQLException {@inheritDoc}
     */
    public Connection getConnection() throws SQLException {
        return source.getConnection();
    }

    /**
     * {@inheritDoc}
     */
    public void close() {
        source.close();
    }

}
