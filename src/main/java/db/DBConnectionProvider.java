package db;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBConnectionProvider {

    private static DBConnectionProvider instance = new DBConnectionProvider();

    private String driverName;
    private String dbURL;
    private String userName;
    private String password;

    private Connection connection;

    private DBConnectionProvider() {
        try {

            loadProperties();
            Class.forName(driverName);
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
    }

    private void loadProperties() throws IOException {
        Properties properties = new Properties();
        properties.load(new FileInputStream("C:\\Users\\Arzuman\\Desktop\\Folder\\web\\TaskManagement\\src\\main\\resources\\config.properties"));

        driverName = properties.getProperty("db.driver.name");
        dbURL = properties.getProperty("db.url");
        userName = properties.getProperty("db.user.name");
        password = properties.getProperty("db.password");

    }

    public static DBConnectionProvider getInstance() {
        return instance;
    }

    public Connection getConnection() {
        try {
            if (connection == null || connection.isClosed()) {
                connection = DriverManager.getConnection(dbURL, userName, password);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(1);
        }
        return connection;
    }
}
