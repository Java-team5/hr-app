package team5.dao.utils;

import java.sql.Connection;
import java.sql.Statement;

public class DBUtils {
    public static void updateByQuery(String sql){
        try {
            Connection connection = DBConnector.getConnection();
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
        } catch (Exception ex){
            ex.printStackTrace();
        }
    }
}
