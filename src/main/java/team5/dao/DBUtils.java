package team5.dao;

import java.sql.Connection;
import java.sql.Statement;

public class DBUtils {
    public static void insertByQuery(String sql){
        try {
            Connection connection = DBConnector.getConnection();
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
        } catch (Exception ex){
            ex.printStackTrace();
        }
    }
}
