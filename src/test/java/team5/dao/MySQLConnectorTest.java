package team5.dao;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import team5.dao.utils.DBConnector;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.Assert.*;

public class MySQLConnectorTest {

    @Test
    public void getConnection() throws ClassNotFoundException,SQLException {
            DBConnector.getConnection();
    }

    @Test
    public void closeConnection() throws ClassNotFoundException,SQLException {
        DBConnector.closeConnection(DBConnector.getConnection());
    }
}