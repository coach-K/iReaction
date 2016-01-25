package database;

/**
 * This class stores a MySQL credential constants
 */
public enum Config {

    /**
     * Username to MySQL database
     */
    USERNAME("root"),

    /**
     * Password to MySQL database
     */
    PASSWORD("codeKenn"),

    /**
     * Location to MySQL database
     */
    URL("jdbc:mysql://localhost:3306"),

    /**
     * MySQL database to establish connection
     */
    DATABASE("reactiondb"),

    /**
     * MySQL table to query
     */
    TABLE("reactions"),
    /**
     * MySQL Driver
     */
    MYSQL_DRIVER("com.mysql.jdbc.Driver");

    private String CONSTANT;

    /**
     * Construct this class
     *
     * @param CONSTANT to be set
     */
    private Config(String CONSTANT) {
        this.CONSTANT = CONSTANT;
    }

    @Override
    public String toString() {
        return CONSTANT;
    }
}
