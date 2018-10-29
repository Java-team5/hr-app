package team5.dao;

import org.junit.Test;
import team5.dao.utils.DBConnector;

import java.io.IOException;
import java.sql.SQLException;

public class MySQLConnectorTest {

    @Test
    public void getConnection() throws ClassNotFoundException, SQLException, IOException {
            DBConnector.getConnection();
    }

    @Test
    public void closeConnection() throws ClassNotFoundException, SQLException, IOException {
        DBConnector.closeConnection(DBConnector.getConnection());
    }
}