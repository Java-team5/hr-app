package team5.dao.utils;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class DBConnector {

    private static String URL;
    private static String USERNAME;
    private static String PASSWORD;
    private static String DRIVER;

    private static Connection connection;
    //private static Properties properties = new Properties();

    private static void setProperties() throws IOException {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("db/db");
        URL = resourceBundle.getString("db.url");
        USERNAME = resourceBundle.getString("db.username");
        PASSWORD = resourceBundle.getString("db.password");
        DRIVER = resourceBundle.getString("db.driver");
    }

    public static Connection getConnection() throws
            SQLException, ClassNotFoundException, IOException {
        if (connection == null) {
            setProperties();
            Class.forName(DRIVER);
            connection =  DriverManager.getConnection(URL, USERNAME, PASSWORD);
        }
        return connection;
    }

    public static void closeConnection(final Connection con) throws SQLException {
        if (connection.equals(con)) {
            if (connection != null) {
                connection.close();
                connection = null;
            }
        }
    }
}
