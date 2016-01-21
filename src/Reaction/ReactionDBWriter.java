package Reaction;


import Database.Config;
import Database.DatabaseConnector;
import Database.DatabaseManager;
import Pair.Pair;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * This class establish a connection to the specified database
 * Sets {@code connected } to {@code false} if an error occur.
 *
 * @author OGUNDE KEHINDE
 * @see Database.DatabaseConnector
 * @see Database.DatabaseManager
 */
public class ReactionDBWriter {

    private DatabaseConnector databaseConnector;
    private DatabaseManager databaseManager;
    private boolean connected = false;

    /**
     * Constructs this class
     *
     * @throws Exception if an error occurs
     */
    public ReactionDBWriter() throws Exception {
        databaseConnector = new DatabaseConnector(Config.URL.toString(), Config.USERNAME.toString(), Config.PASSWORD.toString());
        databaseManager = new DatabaseManager(databaseConnector.connect());
    }

    /**
     * Connect a Reaction Database if not exist with credentials specified in {@link Database.Config}
     *
     * @throws Exception if an error occurs
     */
    public void connectToReactionDatabase() throws Exception {
        databaseManager.createNewDatabase(Config.DATABASE.toString()).execute();
        databaseConnector.setDatabase(Config.DATABASE.toString());
        databaseManager.setConnection(databaseConnector.connect());
        connected = true;
    }

    /**
     * Create a Reaction Table if not exist with credentials specified in {@link Database.Config}
     */
    public void createReactionTable() {
        databaseManager.createNewTable(Config.TABLE.toString())
                .addColumn("UNIQUE-ID", DatabaseManager.TEXT)
                .addColumn("TYPES", DatabaseManager.TEXT)
                .addColumn("COMMON-NAME", DatabaseManager.TEXT)
                .addColumn("ATOM-MAPPINGS", DatabaseManager.TEXT)
                .addColumn("CANNOT-BALANCE?", DatabaseManager.TEXT)
                .addColumn("COMMENT-INTERNAL", DatabaseManager.TEXT)
                .addColumn("CREDITS", DatabaseManager.TEXT)
                .addColumn("DELTAG0", DatabaseManager.TEXT)
                .addColumn("EC-NUMBER", DatabaseManager.TEXT)
                .addColumn("ENZYMATIC-REACTION", DatabaseManager.TEXT)
                .addColumn("IN-PATHWAY", DatabaseManager.TEXT)
                .addColumn("LEFT", DatabaseManager.TEXT)
                .addColumn("MEMBER-SORT-FN", DatabaseManager.TEXT)
                .addColumn("ORPHAN?", DatabaseManager.TEXT)
                .addColumn("PHYSIOLOGICALLY-RELEVANT?", DatabaseManager.TEXT)
                .addColumn("PREDECESSORS", DatabaseManager.TEXT)
                .addColumn("PRIMARIES", DatabaseManager.TEXT)
                .addColumn("REACTION-DIRECTION", DatabaseManager.TEXT)
                .addColumn("REACTION-LIST", DatabaseManager.TEXT)
                .addColumn("RIGHT", DatabaseManager.TEXT)
                .addColumn("RXN-LOCATIONS", DatabaseManager.TEXT)
                .addColumn("SPONTANEOUS?", DatabaseManager.TEXT)
                .addColumn("STD-REDUCTION-POTENTIAL", DatabaseManager.TEXT)
                .addColumn("SYNONYMS", DatabaseManager.TEXT)
                .addColumn("SYSTEMATIC-NAME", DatabaseManager.TEXT)
                .addColumn("^COMPARTMENT", DatabaseManager.TEXT)
                .addColumn("^COEFFICIENT", DatabaseManager.TEXT)
                .execute();
    }

    /**
     * Insert the specified element to database
     *
     * @param reaction element to be inserted to database.
     */
    public void writeReactionToDatabase(Reaction reaction) {
        ArrayList<String> keys = new ArrayList<>();
        ArrayList<String> values = new ArrayList<>();

        for (Pair<String, ArrayList<String>> pair : reaction) {
            keys.add(pair.getKey());
            values.add(pair.getValue().toString());
        }

        databaseManager.insert(keys, values).executeUpdate();
    }

    /**
     * Returns the state of a connection.
     *
     * @return boolean element #connected
     */
    public boolean isConnected() {
        return connected;
    }

    /**
     * Destroys a connection
     *
     * @throws SQLException if error exist
     */
    public void disconnect() throws SQLException {
        databaseConnector.disconnect();
        databaseManager.disconnect();
        connected = false;
    }
}
