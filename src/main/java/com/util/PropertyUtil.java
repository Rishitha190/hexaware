package com.util;
import java.io.FileInputStream;
import java.util.Properties;
public class PropertyUtil {
	private static final String PROPERTY_FILE = "database.properties";

    public static String getPropertyString() {
        Properties properties = new Properties();
        try (FileInputStream input = new FileInputStream(PROPERTY_FILE)) {
            properties.load(input);
            String hostname = properties.getProperty("hostname");
            String dbname = properties.getProperty("dbname");
            String username = properties.getProperty("username");
            String password = properties.getProperty("password");
            String port = properties.getProperty("port");
            String connectionString = "jdbc:mysql://" + hostname + ":" + port + "/" + dbname + "?user=" + username + "&password=" + password;

            return connectionString;
        } catch (Exception e) {
        	e.printStackTrace();
        }
        	return null;
    }
}
