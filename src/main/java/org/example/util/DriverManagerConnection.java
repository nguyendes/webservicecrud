package org.example.util;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DriverManagerConnection {

    private static String url;
    private static String user;
    private static String password;
    private static String driver;

    static {
        try (InputStream input = DriverManagerConnection.class.getClassLoader().getResourceAsStream("config.xml")) {
            if (input == null) {
                throw new RuntimeException("Cannot find config.xml in classpath");
            }
            Properties props = new Properties();
            props.loadFromXML(input);
            url = props.getProperty("db.url");
            user = props.getProperty("db.user");
            password = props.getProperty("db.password");
            driver = props.getProperty("db.driver");

            if (url == null || user == null || password == null || driver == null) {
                throw new RuntimeException("Missing connection information in config.xml");
            }

            Class.forName(driver);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        if (url == null || user == null || password == null) {
            throw new SQLException("Connection information is incomplete. Check config.xml.");
        }
        return DriverManager.getConnection(url, user, password);
    }
}
