package Database;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.ResultSet;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class DatabaseManagerTest {

    DatabaseConnector databaseConnector;
    DatabaseManager databaseManager;

    @Before
    public void setUp() throws Exception {
        databaseConnector = new DatabaseConnector(Config.URL.toString(), Config.USERNAME.toString(), Config.PASSWORD.toString(), Config.MYSQL_DRIVER.toString());
        databaseManager = new DatabaseManager(databaseConnector.connect());
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testCreateNewDatabase() throws Exception {
        int flag = databaseManager.createNewDatabase("NEW_DATABASE")
                .execute();
        assertEquals(flag, 1);
    }

    private void connectToDatabase() throws Exception{
        databaseConnector.setDatabase("NEW_DATABASE");
        databaseManager.setConnection(databaseConnector.connect());
    }

    @Test
    public void testCreateNewTable() throws Exception {
        connectToDatabase();
        int flag = databaseManager.createNewTable("NEW_TABLE")
                .addPrimaryKey("id")
                .addColumn("FIRSTNAME")
                .addColumn("LASTNAME")
                .execute();
        assertEquals(flag, 0);
    }

    @Test
    public void testGetTable() throws Exception {
        testCreateNewTable();
        assertEquals(databaseManager.getTable(), "NEW_TABLE");
    }

    @Test
    public void testSetTable() throws Exception {
        assertNull(databaseManager.getTable());
        databaseManager.setTable("FRESH_TABLE");
        assertEquals(databaseManager.getTable(), "FRESH_TABLE");
    }

    @Test
    public void testAddColumn() throws Exception {
        testCreateNewTable();
    }

    @Test
    public void testAddColumn1() throws Exception {
        testCreateNewTable();
    }

    @Test
    public void testAddPrimaryKey() throws Exception {
        testCreateNewTable();
    }

    @Test
    public void testAddPrimaryKey1() throws Exception {
        testCreateNewTable();
    }

    private ArrayList<String> fetchValues(){
        ArrayList<String> values = new ArrayList<>();
        values.add("0");
        values.add("NENGI");
        values.add("GRACE");
        return values;
    }

    private ArrayList<String> fetchField(){
        ArrayList<String> fields = new ArrayList<>();
        fields.add("firstname");
        return fields;
    }

    private ArrayList<String> fetchValue(){
        ArrayList<String> value = new ArrayList<>();
        value.add("CHIDI");
        return value;
    }

    private ArrayList<String> fetchValue2(){
        ArrayList<String> value = new ArrayList<>();
        value.add("DAMILOLA");
        return value;
    }

    private void deleteAll(){
        databaseManager.delete().executeUpdate();
    }

    private void insertSelect() throws Exception {
        testCreateNewTable();
        deleteAll();
        databaseManager.insert(fetchValues()).executeUpdate();
        ResultSet resultSet = databaseManager.select().executeQuery();

        if (resultSet != null) {
            while (resultSet.next()) {
                assertNotNull(resultSet.getInt("id"));
                assertNotNull(resultSet.getString("firstname"), "NENGI");
                assertNotNull(resultSet.getString("lastname"), "GRACE");
                break;
            }
        }
    }

    private void insertSelect1() throws Exception {
        testCreateNewTable();
        deleteAll();
        databaseManager.insert(fetchField(), fetchValue()).executeUpdate();
        ResultSet resultSet = databaseManager.select().where("firstname", "CHIDI").executeQuery();

        if (resultSet != null) {
            while (resultSet.next()) {
                assertEquals(resultSet.getString("firstname"), "CHIDI");
                break;
            }
        }
    }

    @Test
    public void testSelect() throws Exception {
        insertSelect();
    }

    @Test
    public void testSelect1() throws Exception {
        insertSelect1();
    }

    @Test
    public void testInsert() throws Exception {
        insertSelect();
    }

    @Test
    public void testInsert1() throws Exception {
        insertSelect1();
    }

    @Test
    public void testUpdate() throws Exception {
        testCreateNewTable();
        deleteAll();
        insertSelect();
        databaseManager.update(fetchField(), fetchValue2()).executeUpdate();
        ResultSet resultSet = databaseManager.select().where("firstname", "DAMILOLA").executeQuery();

        if (resultSet != null) {
            while (resultSet.next()) {
                assertEquals(resultSet.getString("firstname"), "DAMILOLA");
                break;
            }
        }
    }

    @Test
    public void testDelete() throws Exception {
        testCreateNewTable();
        deleteAll();
    }

    @Test
    public void testWhere() throws Exception {
        testCreateNewTable();
        deleteAll();
        insertSelect();
        databaseManager.update(fetchField(), fetchValue2()).executeUpdate();
        ResultSet resultSet = databaseManager.select().where("firstname", "DAMILOLA").executeQuery();

        if (resultSet != null) {
            while (resultSet.next()) {
                assertEquals(resultSet.getString("firstname"), "DAMILOLA");
                break;
            }
        }
    }

    @Test
    public void testAnd() throws Exception {
        testCreateNewTable();
        deleteAll();
        insertSelect();
        ResultSet resultSet = databaseManager.select().where("firstname", "NENGI").and("lastname", "GRACE").executeQuery();

        if (resultSet != null) {
            while (resultSet.next()) {
                assertEquals(resultSet.getString("firstname"), "NENGI");
                break;
            }
        }
    }

    @Test
    public void testWhereLike() throws Exception {
        testCreateNewTable();
        deleteAll();
        insertSelect();
        ResultSet resultSet = databaseManager.select().whereLike("firstname", "EN").executeQuery();

        if (resultSet != null) {
            while (resultSet.next()) {
                assertEquals(resultSet.getString("firstname"), "NENGI");
                break;
            }
        }
    }

    @Test
    public void testAndLike() throws Exception {
        testCreateNewTable();
        deleteAll();
        insertSelect();
        ResultSet resultSet = databaseManager.select().whereLike("firstname", "NG").andLike("lastname", "AC").executeQuery();

        if (resultSet != null) {
            while (resultSet.next()) {
                assertEquals(resultSet.getString("firstname"), "NENGI");
                assertEquals(resultSet.getString("lastname"), "GRACE");
                break;
            }
        }
    }

    @Test
    public void testExecuteQuery() throws Exception {
        testCreateNewTable();
        deleteAll();
        insertSelect1();
    }

    @Test
    public void testExecute() throws Exception {
        testCreateNewTable();
        deleteAll();
        insertSelect();
    }

    @Test
    public void testExecuteUpdate() throws Exception {
        testCreateNewTable();
        deleteAll();
        insertSelect();
    }

    @Test
    public void testDisconnect() throws Exception {
        testCreateNewTable();
        databaseConnector.disconnect();
        databaseManager.disconnect();
        assertFalse(databaseConnector.isActive());
        assertNull(databaseManager.getConnection());
    }

    @Test
    public void testSetConnection() throws Exception {
        connectToDatabase();
        testDisconnect();
    }
}