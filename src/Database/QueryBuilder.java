package database;


import java.util.ArrayList;

/**
 * This builder class prepares a simple SQL statement
 * This statements can be used by {@code PreparedStatement, Statement}
 * <p/>
 * <p>The principal operations on a {@code QueryBuilder} are the
 * {@code create, insert, update} and {@code delete} methods, which
 * accept a {@code @Nullable ArrayList} data of type {@code String}. Each effectively
 * create simple SQL statement and then {@code build}
 * completes the statement for execution.
 * <p/>
 *
 * @author OGUNDE KEHINDE
 * @see java.util.ArrayList
 * @see java.lang.StringBuilder
 */
public class QueryBuilder {

    /**
     * Static constants
     */
    public static final String INTEGER = "INTEGER";
    public static final String FLOAT = "FLOAT";
    public static final String TEXT = "TEXT";
    public static final String BOOLEAN = "BOOLEAN";

    /**
     * Stores table name
     */
    private String table;

    /**
     * Stores prepared statements
     */
    private StringBuilder query;

    /**
     * Constructs the class
     */
    public QueryBuilder() {
    }

    /**
     * Constructs this class with a table name
     * Accepts table to perform operations
     *
     * @param table to perform operations
     */
    public QueryBuilder(String table) {
        this.table = table;
    }

    /**
     * Prepare statement create new database
     * Accepts database to perform operations
     * Returns this context
     *
     * @param database to perform operations
     * @return this context
     */
    public QueryBuilder database(String database) {
        initializeBuilder("CREATE DATABASE IF NOT EXISTS " + database);
        return this;
    }

    /**
     * Prepare statement create new table
     * Returns this context
     *
     * @return this context
     */
    public QueryBuilder table() {
        initializeBuilder("CREATE TABLE IF NOT EXISTS " + this.table + " ( ");
        return this;
    }

    /**
     * Append column statement to the existing statement.
     * Accepts columnName to be appended to existing statement
     *
     * @param columnName to be appended to existing statement
     * @return this context
     */
    public QueryBuilder addColumn(String columnName) {
        appendColumn(columnName, columnType(""));
        query.append(" , ");
        return this;
    }

    /**
     * Append column statement to the existing statement.
     * Append TYPE to existing statement.
     * Accept columnName to be appended to existing statement
     * Accept TYPE to define a column type {@literal VARCHAR, TEXT, INTEGER, DOUBLE}
     *
     * @param columnName to be appended to existing query.
     * @param TYPE       to define a column
     * @return this context
     */
    public QueryBuilder addColumn(String columnName, String TYPE) {
        appendColumn(columnName, columnType(TYPE));
        query.append(" , ");
        return this;
    }

    /**
     * Append column statement to the existing statement.
     * Append TYPE to existing statement.
     * Append primaryKey field if and only if
     * {@code primaryKey} is set to <tt>true</tt>
     * <p/>
     * Accept columnName to be appended to existing statement
     * Accept TYPE to define a column type {@literal VARCHAR, TEXT, INTEGER, DOUBLE}
     * Accept primaryKey to define a field as primary key
     *
     * @param columnName to be appended to existing query.
     * @param TYPE       to define a column
     * @param primaryKey to define a field as primary key
     * @return this context
     */
    public QueryBuilder addColumn(String columnName, String TYPE, boolean primaryKey) {
        appendColumn(columnName, TYPE);
        query.append(primaryKey && TYPE.equals(INTEGER) ? " primary key unique auto_increment " : " primary key unique ");
        query.append(" , ");
        return this;
    }

    /**
     * Complete the statement.
     * Returns a complete statement
     *
     * @return a complete statement
     */
    public String build() {
        String queryString = query.toString().trim();
        initializeBuilder(queryString);

        if (query.charAt(query.length() - 1) == ',') {
            query = removeLastChar(query, ",");
            query.append(" ) ");
            return query.toString();
        } else return query.toString();
    }

    /**
     * Remove the last specified character from the specified string builder
     * Return refactor {@code StringBuilder} element.
     *
     * @param stringBuilder to search
     * @param character     to be removed
     * @return refactor {@code StringBuilder} element.
     */
    private StringBuilder removeLastChar(StringBuilder stringBuilder, String character) {
        return stringBuilder.deleteCharAt(stringBuilder.lastIndexOf(character));
    }

    /**
     * Returns an SQL statement corresponding to the specified element
     *
     * @param TYPE to get its corresponding SQL statement
     * @return part of an SQL statement
     */
    private String columnType(String TYPE) {
        switch (TYPE) {
            case INTEGER: return (" INT ");
            case TEXT: return (" TEXT ");
            case FLOAT: return (" DOUBLE(100) ");
            case BOOLEAN: return (" TINYINT(1) ");
            default: return (" VARCHAR(100) ");
        }
    }

    /**
     * Builds SQL SELECT statement
     *
     * @return this context
     */
    public QueryBuilder select() {
        initializeBuilder("SELECT * FROM " + this.table);
        return this;
    }

    /**
     * Builds SQL SELECT statement with the specified fields
     *
     * @param keys fields to the included in the SQL statement
     * @return this context
     */
    public QueryBuilder select(ArrayList<String> keys) {
        initializeBuilder(" SELECT ");

        for (String key : keys)
            appendQuery(key + " , ");

        query = removeLastChar(query, ",");
        appendQuery(" FROM " + this.table);
        return this;
    }

    /**
     * Builds SQL INSERT statement with the specified fields
     *
     * @param keys fields to the included in the SQL statement
     * @return this context
     */
    public QueryBuilder insert(ArrayList<String> keys) {
        initializeBuilder("INSERT INTO " + this.table);

        StringBuilder sb1 = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();
        sb1.append(" ( ");
        sb2.append(" ( ");

        for (String key : keys) {
            sb1.append('`');
            sb1.append(key);
            sb1.append("`, ");
            sb2.append(" ? ");
            sb2.append(", ");
        }

        sb1 = removeLastChar(sb1, ",");
        sb2 = removeLastChar(sb2, ",");
        sb1.append(" ) ");
        sb2.append(" ) ");

        appendQuery(sb1.toString() + " VALUES " + sb2.toString());
        return this;
    }

    /**
     * Builds SQL INSERT statement for the specified size of values
     *
     * @param size fields to the included in the SQL statement
     * @return this context
     */
    public QueryBuilder insert(int size) {
        initializeBuilder("INSERT INTO " + this.table + " VALUES ( ");

        while (size > 0) {
            appendQuery(" ? " + ", ");
            size--;
        }

        query = removeLastChar(query, ",");
        query.append(" ) ");
        return this;
    }

    /**
     * Builds SQL UPDATE statement with the specified fields
     *
     * @param keys fields to the included in the SQL statement
     * @return this context
     */
    public QueryBuilder update(ArrayList<String> keys) {
        initializeBuilder("UPDATE " + this.table + " SET ");

        for (String key : keys)
            appendQuery(key + " = ? " + " , ");

        query = removeLastChar(query, ",");
        return this;
    }

    /**
     * Builds SQL DELETE statement
     *
     * @return this context
     */
    public QueryBuilder delete() {
        initializeBuilder("DELETE FROM " + this.table);
        return this;
    }

    /**
     * Append SQL WHERE statement with the specified fields
     *
     * @param field to the included in the SQL statement
     * @return this context
     */
    public QueryBuilder where(String field) {
        appendQuery(" WHERE " + field + " = ? ");
        return this;
    }

    /**
     * Append SQL AND statement with the specified fields
     *
     * @param field to the included in the SQL statement
     * @return this context
     */
    public QueryBuilder and(String field) {
        appendQuery(" AND " + field + " = ? ");
        return this;
    }

    /**
     * Append SQL WHERE LIKE statement with the specified fields
     *
     * @param field to the included in the SQL statement
     * @return this context
     */
    public QueryBuilder whereLike(String field) {
        appendQuery(" WHERE " + field + " LIKE ? ");
        return this;
    }

    /**
     * Append SQL ANDLIKE statement with the specified fields
     *
     * @param field to the included in the SQL statement
     * @return this context
     */
    public QueryBuilder andLike(String field) {
        appendQuery(" AND " + field + " LIKE ? ");
        return this;
    }

    private void initializeBuilder(String SQL){
        query = new StringBuilder();
        query.append(SQL);
    }

    private void appendColumn(String columnName, String type){
        query.append("`");
        query.append(columnName);
        query.append("`");
        query.append(columnType(type));
    }

    private void appendQuery(String SQL){
        query.append(SQL);
    }
}
