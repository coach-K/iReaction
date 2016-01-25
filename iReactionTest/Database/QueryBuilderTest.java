package database;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class QueryBuilderTest {

    QueryBuilder queryBuilder;
    String query;

    @Before
    public void setUp() throws Exception {
        queryBuilder = new QueryBuilder("TABLE_NAME");
    }

    @After
    public void tearDown() throws Exception {

    }

    private void testTableAndColumn() {
        query = queryBuilder.table()
                .addColumn("ID", QueryBuilder.INTEGER, true)
                .addColumn("FIRSTNAME").addColumn("LASTNAME")
                .build();
        assertEquals(query, "CREATE TABLE IF NOT EXISTS TABLE_NAME ( `ID` INT  primary key unique auto_increment  , `FIRSTNAME` VARCHAR(100)  , `LASTNAME` VARCHAR(100)   ) ");
    }

    @Test
    public void testDatabase() throws Exception {
        query = queryBuilder.database("DATABASE_NAME")
                .build();
        assertEquals(query, "CREATE DATABASE IF NOT EXISTS DATABASE_NAME");
    }

    @Test
    public void testTable() throws Exception {
        testTableAndColumn();
    }

    @Test
    public void testAddColumn() throws Exception {
        testTableAndColumn();
    }

    @Test
    public void testAddColumn1() throws Exception {
        testTableAndColumn();
    }

    @Test
    public void testAddColumn2() throws Exception {
        testTableAndColumn();
    }

    @Test
    public void testBuild() throws Exception {
        testTableAndColumn();
    }

    @Test
    public void testSelect() throws Exception {
        query = queryBuilder.select().build();
        assertEquals(query, "SELECT * FROM TABLE_NAME");
    }

    private ArrayList<String> addFields() {
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("FIRSTNAME");
        arrayList.add("LASTNAME");
        return arrayList;
    }

    @Test
    public void testSelect1() throws Exception {
        query = queryBuilder.select(addFields()).build();
        assertEquals(query, "SELECT FIRSTNAME , LASTNAME   FROM TABLE_NAME");
    }

    @Test
    public void testInsert() throws Exception {
        query = queryBuilder.insert(addFields()).build();
        assertEquals(query, "INSERT INTO TABLE_NAME ( `FIRSTNAME`, `LASTNAME`  )  VALUES  (  ? ,  ?   )");
    }

    @Test
    public void testInsert1() throws Exception {
        query = queryBuilder.insert(addFields().size()).build();
        assertEquals(query, "INSERT INTO TABLE_NAME VALUES (  ? ,  ?   )");
    }

    @Test
    public void testUpdate() throws Exception {
        query = queryBuilder.update(addFields()).build();
        assertEquals(query, "UPDATE TABLE_NAME SET FIRSTNAME = ?  , LASTNAME = ?");
    }

    @Test
    public void testDelete() throws Exception {
        query = queryBuilder.delete().build();
        assertEquals(query, "DELETE FROM TABLE_NAME");
    }

    @Test
    public void testWhere() throws Exception {
        query = queryBuilder.select(addFields())
                .where("FIRSTNAME").build();
        assertEquals(query, "SELECT FIRSTNAME , LASTNAME   FROM TABLE_NAME WHERE FIRSTNAME = ?");
    }

    @Test
    public void testAnd() throws Exception {
        query = queryBuilder.select(addFields())
                .where("FIRSTNAME").and("LASTNAME").build();
        assertEquals(query, "SELECT FIRSTNAME , LASTNAME   FROM TABLE_NAME WHERE FIRSTNAME = ?  AND LASTNAME = ?");
    }

    @Test
    public void testWhereLike() throws Exception {
        query = queryBuilder.select(addFields())
                .whereLike("FIRSTNAME").build();
        assertEquals(query, "SELECT FIRSTNAME , LASTNAME   FROM TABLE_NAME WHERE FIRSTNAME LIKE ?");
    }

    @Test
    public void testAndLike() throws Exception {
        query = queryBuilder.select(addFields())
                .whereLike("FIRSTNAME").andLike("LASTNAME").build();
        assertEquals(query, "SELECT FIRSTNAME , LASTNAME   FROM TABLE_NAME WHERE FIRSTNAME LIKE ?  AND LASTNAME LIKE ?");
    }
}