package Database;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * This class creates a <code>Connection</code> object's database is able to provide information
 * describing its tables, its supported SQL grammar, its stored
 * procedures, the capabilities of this connection, and so on.
 *
 * @author OGUNDE KEHINDE
 * @see DriverManager#getConnection
 * @see java.sql.Statement
 * @see java.sql.ResultSet
 * @see java.sql.DatabaseMetaData
 */
public class DatabaseConnector {

    /**
     * <P>A connection (session) with a specific
     * database. SQL statements are executed and results are returned
     * within the context of a connection.
     * <p/>
     */
    private Connection connection;

    /**
     * stores universal resource locator to a database
     */
    private String url;

    /**
     * stores username to a database
     */
    private String username;

    /**
     * stores password to a database
     */
    private String password;

    /**
     * stores a database name
     */
    private String database;

    /**
     * constructs this class with the specified argument
     *
     * @param url to a database
     * @param username to a database
     * @param password to a database
     */
    public DatabaseConnector(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }

    /**
     * constructs this class with the specified arguments
     *
     * @param url to a database
     * @param username to a database
     * @param password to a database
     * @param database to a database
     */
    public DatabaseConnector(String url, String username, String password, String database) {
        this(url, username, password);
        this.database = database;
    }

    /**
     * Try to create a database connection
     * if and only if this url, username and password
     * is not <code>null</code>.
     * Returns a Connection element if and only if
     * connection throws no errors.
     *
     * @return a connection elemet
     * @throws java.lang.Exception
     */
    public Connection connect() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            this.connection = (this.database != null) ?
                    DriverManager.getConnection(this.url + "/" + database, this.username, this.password) :
                    DriverManager.getConnection(this.url, this.username, this.password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }

    /**
     * Disconnect this connection if its been active
     *
     * @throws SQLException
     */
    public void disconnect() throws SQLException {
        if (connection != null)
            connection.close();
    }

    public String getDatabase() {
        return this.database;
    }

    public void setDatabase(String database) {
        this.database = database;
    }
}
