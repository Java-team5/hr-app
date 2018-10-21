package team5.dao;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLConnector {
    private static final String URL="jdbc:mysql://localhost:3306/hr_app_db" +
            "?verifyServerCertificate=false"+
            "&useSSL=false"+
            "&requireSSL=false"+
            "&useLegacyDatetimeCode=false"+
            "&amp"+
            "&serverTimezone=UTC";
    private static final String USERNAME="root";
    private static final String PASSWORD="";
    private static boolean singletonFlag = false;
    private static Connection connection = null;

    public Connection getConnection() {
        return connection != null ? connection : null;
    }

    private MySQLConnector() throws SQLException, ClassNotFoundException{
        Class.forName("com.mysql.jdbc.Driver");
        MySQLConnector.connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }

    public static MySQLConnector getMySQLConnector() throws SQLException, ClassNotFoundException{
        try {
            return !singletonFlag ? new MySQLConnector() : null;
        }
        catch (SQLException e){
            throw e;
        }
        catch (ClassNotFoundException e){
            throw new ClassNotFoundException("JDBC Driver was not found");
        }
    }

    public static void close(MySQLConnector connector) throws SQLException{
        if(MySQLConnector.singletonFlag){
            connector = null;
            MySQLConnector.connection.close();
            MySQLConnector.singletonFlag = false;
        }
    }
}