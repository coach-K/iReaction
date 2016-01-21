package Database;


import java.sql.*;
import java.util.ArrayList;

/**
 * This class accept active <code>Connection</code> object
 * This class manages every CRUD activities that is executed
 * on this connection.
 * It prepares an SQL statement and performs SQL actions
 * at the call to {@code execute() executeQuery()} and {@code executeUpdate()}
 *
 * @author OGUNDE KEHINDE
 * @see Database.QueryBuilder
 * @see DriverManager#getConnection
 * @see java.sql.Statement
 * @see java.sql.ResultSet
 * @see java.sql.DatabaseMetaData
 */
public class DatabaseManager {

    /**
     * Static constants
     */
    public static final String INTEGER = "INTEGER";
    public static final String FLOAT = "FLOAT";
    public static final String TEXT = "TEXT";
    public static final String BOOLEAN = "BOOLEAN";

    /**
     * Stores an active connection to a database
     */
    private Connection connection;

    /**
     * Stores a statement
     */
    private Statement statement;

    /**
     * Stores a prepared statement
     */
    private PreparedStatement preparedStatement;

    /**
     * Stores a result set
     */
    private ResultSet resultSet;
    private QueryBuilder queryBuilder;
    private String table;
    private ArrayList<String> values;

    /**
     * Construct this class with the specified element.
     * Accepts a connection to perform SQL operation.
     *
     * @param connection to perform operation
     */
    public DatabaseManager(Connection connection) {
        this.connection = connection;
        this.values = new ArrayList<>();
    }

    /**
     * Construct this class with the specified elements
     * Accepts a connection to perform SQL operations
     * Accepts a table to perform CRUD operations.
     *
     * @param connection to perform SQL operations
     * @param table      to perform CRUD operations
     */
    public DatabaseManager(Connection connection, String table) {
        this(connection);
        this.table = table;
    }

    /**
     * Prepares an SQL create new database query with the specified element
     * Accept a database to be created
     *
     * @param database to be created
     * @return this context
     */
    public DatabaseManager createNewDatabase(String database) {
        this.values = new ArrayList<>();
        queryBuilder = new QueryBuilder().database(database);
        return this;
    }

    /**
     * Prepares an SQL create new table query with the specified element
     * Accept a table to be created
     *
     * @param table to be created
     * @return this context
     */
    public DatabaseManager createNewTable(String table) {
        this.values = new ArrayList<>();
        this.table = table;
        queryBuilder = new QueryBuilder(this.table).table();

        try {
            statement = connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return this;
    }

    /**
     * get table name
     *
     * @return this table name
     */
    public String getTable() {
        return table;
    }

    /**
     * set table name
     * <p/>
     * Accept table name
     */
    public void setTable(String table) {
        this.table = table;
    }

    /**
     * Append specified element to the this statement
     * <p/>
     * Accept field name to be appended to the existing statement
     *
     * @param name to be appended to the existing statement
     * @return this context
     */
    public DatabaseManager addColumn(String name) {
        queryBuilder.addColumn(name);
        return this;
    }

    /**
     * Append specified elements to the this statement
     * <p/>
     * Accept field name to be appended to the existing statement
     * Accept field type to be appended to the existing statement
     *
     * @param name to be appended to the existing statement
     * @param type to be appended to the existing statement
     * @return this context
     */
    public DatabaseManager addColumn(String name, String type) {
        queryBuilder.addColumn(name, type);
        return this;
    }


    /**
     * Create a primaryKey column
     * Append specified elements to the this statement
     * <p/>
     * Accept field name to be appended to the existing statement
     * as Primary Key
     *
     * @param name to be appended to the existing statement
     * @return this context
     */
    public DatabaseManager addPrimaryKey(String name) {
        queryBuilder.addColumn(name, QueryBuilder.INTEGER, true);
        return this;
    }

    /**
     * Create a primaryKey column
     * Append specified elements to the this statement
     * <p/>
     * Accept field name to be appended to the existing statement
     * as Primary Key
     *
     * @param name to be appended to the existing statement
     * @param type to be appended to the existing statement
     * @return this context
     */
    public DatabaseManager addPrimaryKey(String name, String type) {
        queryBuilder.addColumn(name, type, true);
        return this;
    }

    /**
     * Creates SQL SELECT statement
     *
     * @return this context
     */
    public DatabaseManager select() {
        this.values = new ArrayList<>();
        queryBuilder = new QueryBuilder(this.table).select();
        return this;
    }

    /**
     * Creates SQL SELECT statement with the specified fields
     *
     * @param keys fields to select from
     * @return this context
     */
    public DatabaseManager select(ArrayList<String> keys) {
        this.values = new ArrayList<>();
        queryBuilder = new QueryBuilder(this.table).select(keys);
        return this;
    }

    /**
     * Creates SQL INSERT statement with the specified values
     *
     * @param values values to be inserted
     * @return this context
     */
    public DatabaseManager insert(ArrayList<String> values) {
        this.values = values;
        queryBuilder = new QueryBuilder(this.table).insert(values.size());
        return this;
    }

    /**
     * Creates SQL INSERT statement with the specified fields and values
     *
     * @param values values to be inserted
     * @param keys   fields to be insert to
     * @return this context
     */
    public DatabaseManager insert(ArrayList<String> keys, ArrayList<String> values) {
        this.values = values;
        queryBuilder = new QueryBuilder(this.table).insert(keys);
        return this;
    }

    /**
     * Create SQL UPDATE statement with the specified fields
     *
     * @param keys fields to be updated
     * @return this context
     */
    public DatabaseManager update(ArrayList<String> keys, ArrayList<String> values) {
        this.values = values;
        queryBuilder = new QueryBuilder(this.table).update(keys);
        return this;
    }

    /**
     * Create SQL DELETE statement
     *
     * @return this context
     */
    public DatabaseManager delete() {
        this.values = new ArrayList<>();
        queryBuilder = new QueryBuilder(this.table).delete();
        return this;
    }

    /**
     * Append SQL WHERE statement with the specified fields
     *
     * @param key   to query
     * @param value corresponding value
     * @return this context
     */
    public DatabaseManager where(String key, String value) {
        this.values.add(value);
        queryBuilder.where(key);
        return this;
    }

    /**
     * Append SQL AND statement with the specified fields
     *
     * @param key   to query
     * @param value corresponding value
     * @return this context
     */
    public DatabaseManager and(String key, String value) {
        this.values.add(value);
        queryBuilder.and(key);
        return this;
    }

    /**
     * Append SQL WHERE LIKE statement with the specified fields
     *
     * @param key   to query
     * @param value corresponding value
     * @return this context
     */
    public DatabaseManager whereLike(String key, String value) {
        this.values.add("%" + value + "%");
        queryBuilder.whereLike(key);
        return this;
    }

    /**
     * Append SQL AND LIKE statement with the specified fields
     *
     * @param key   to query
     * @param value corresponding value
     * @return this context
     */
    public DatabaseManager andLike(String key, String value) {
        this.values.add(value);
        queryBuilder.andLike(key);
        return this;
    }

    /**
     * Execute a prepared statement if fields are specified
     * Execute a create statement if fields are not specified
     * such as SQL SELECT, SELECT FIELD
     * Returns a {@code ResultSet}
     *
     * @return a {@code ResultSet}
     */
    public ResultSet executeQuery() {
        try {
            if (values.size() > 0) {
                preparedStatement = connection.prepareStatement(queryBuilder.build());
                int count = 1;

                for (String value : values) {
                    preparedStatement.setObject(count++, value);
                }

                resultSet = preparedStatement.executeQuery();
            } else {
                statement = connection.createStatement();
                resultSet = statement.executeQuery(queryBuilder.build());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
    }

    /**
     * Execute a create statement and returns an integer value <tt>n</tt>
     * such that {@code <tt>n</tt> >= 0}
     * {@literal example: number of rows affected 0, 1, ... n}
     *
     * @return an integer value
     */
    public int execute() {
        int flag = 0;
        try {
            if (values.size() == 0) {
                statement = connection.createStatement();

                flag = statement.executeUpdate(queryBuilder.build());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * Execute a prepared statement if fields are specified
     * such as INSERT, UPDATE and DELETE
     * Returns a {@code ResultSet}
     *
     * @return a {@code ResultSet}
     */
    public void executeUpdate() {
        try {
            if (values.size() > 0) {
                preparedStatement = connection.prepareStatement(queryBuilder.build());
                int count = 1;

                for (String value : values) {
                    preparedStatement.setObject(count++, value);
                }

                preparedStatement.executeUpdate();
            } else {
                statement = connection.createStatement();
                statement.executeUpdate(queryBuilder.build());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Disconnects this connection
     */
    public void disconnect() {
        connection = null;
        statement = null;
        preparedStatement = null;
        queryBuilder = null;
    }

    /**
     * Returns this connection
     *
     * @return this connection
     */
    public Connection getConnection() {
        return this.connection;
    }

    /**
     * Set a connection with the specified element
     *
     * @param connection
     */
    public void setConnection(Connection connection) {
        this.connection = connection;
    }
}
