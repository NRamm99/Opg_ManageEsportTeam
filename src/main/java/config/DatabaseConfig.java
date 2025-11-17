package config;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import exceptions.DbPropertiesNotFound;

public class DatabaseConfig {

    private DatabaseConfig() {
    }

    private static Properties props = new Properties();

    static {
        try (InputStream input = DatabaseConfig.class.getResourceAsStream("/db.properties")) {

            if (input == null) {
                throw new DbPropertiesNotFound();
            }

            props.load(input);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
                props.getProperty("db.url"),
                props.getProperty("db.user"),
                props.getProperty("db.password"));
    }
}
