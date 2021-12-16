package nl.iobyte.achiever.generic.database.objects.types;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import nl.iobyte.achiever.Achiever;
import nl.iobyte.achiever.generic.database.objects.AbstractDatabase;
import java.sql.Connection;
import java.sql.SQLException;

public class MySQLDatabase extends AbstractDatabase {

    private final String dbName;
    private final HikariDataSource source;

    public MySQLDatabase(String id, String url, String host, int port, String dbName, String username, String password) {
        super(id);
        if(url == null || url.isEmpty())
            url = "jdbc:mysql://%host%:%port%/%database%?useSSL=false";

        this.dbName = dbName;
        HikariConfig config = new HikariConfig();
        config.setUsername(username);
        config.setPassword(password);
        config.setDriverClassName("com.mysql.cj.jdbc.Driver");
        config.setMaximumPoolSize(5);
        config.setMaxLifetime(5 * 60 * 1000);
        config.setJdbcUrl(
                url.replace("%host%", host)
                        .replace("%port%", String.valueOf(port))
                        .replace("%database%", dbName)
        );
        source = new HikariDataSource(config);
    }

    /**
     * Get Connection
     * @return Connection
     */
    public Connection getConnection() throws SQLException {
        return source.getConnection();
    }

    /**
     * Close connection
     */
    public void close() {
        Achiever.getInstance().getLogging().info("Closing the database connection for "+dbName);
        source.close();
    }

}