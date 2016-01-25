package database;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;

import static org.junit.Assert.*;

public class DatabaseConnectorTest {

    DatabaseConnector databaseConnector;

    @Before
    public void setUp() throws Exception {
        databaseConnector = new DatabaseConnector(Config.URL.toString(), Config.USERNAME.toString(), Config.PASSWORD.toString(),Config.DATABASE.toString(), Config.MYSQL_DRIVER.toString());
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testConnect() throws Exception {
        Connection connection = null;
        try {
            connection = databaseConnector.connect();
            assertNotNull(connection);
        } catch (Exception e) {
            assertTrue(e.toString().contains("SQLException"));
            assertNull(connection);
        }
    }

    @Test
    public void testDisconnect() throws Exception {
        Connection connection = null;
        try {
            databaseConnector.connect();
            assertTrue(databaseConnector.isActive());
            databaseConnector.disconnect();
            assertFalse(databaseConnector.isActive());
        } catch (Exception e) {
            assertTrue(e.toString().contains("SQLException"));
            assertNull(connection);
        }
    }
}