package team5.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBConnector {

    private static Properties properties = new Properties();

    //TODO change sqlconnection properties to properties file
    private static final String URL="jdbc:mysql://localhost:3306/hr_app_db ?verifyServerCertificate=false &useSSL=false &requireSSL=false &useLegacyDatetimeCode=false &amp &serverTimezone=UTC";
    private static final String USERNAME = "root";
    private static final String PASSWORD="314159";
    public static final String PATH_TO_PROPERTIES = "src/main/resources/config.properties";

    private static Connection connection;

    public static Connection getConnection() throws SQLException,ClassNotFoundException{
        if (connection == null)
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
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
