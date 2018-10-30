package team5.dao.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.ResourceBundle;

public class DBConnector {

    private static String URL;
    private static String USERNAME;
    private static String PASSWORD;
    private static String DRIVER;

    private static Connection connection;
    private static Properties properties = new Properties();

    private static void setProperties() throws IOException {
        //properties.load(new FileInputStream("src/main/resources/db.properties"));
        URL = "jdbc:mysql://localhost:3306/hr_app_db ?verifyServerCertificate=false &useSSL=false &requireSSL=false &useLegacyDatetimeCode=false &amp &serverTimezone=UTC";
        USERNAME = "root";
        PASSWORD = "graffiti3004";
        DRIVER = "com.mysql.cj.jdbc.Driver";
    }

    public static Connection getConnection() throws SQLException, ClassNotFoundException, IOException{
        if (connection == null)
        {
            setProperties();
            Class.forName(DRIVER);
            connection =  DriverManager.getConnection(URL,USERNAME,PASSWORD);
        }
        return connection;
    }

    public static void closeConnection(Connection con) throws SQLException{
        if(connection.equals(con))
            if(connection!=null)
            {
                connection.close();
                connection=null;
            }
    }

}
