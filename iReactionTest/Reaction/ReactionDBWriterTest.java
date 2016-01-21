package Reaction;

import Pair.Pair;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import java.util.ArrayList;

public class ReactionDBWriterTest {

    Pair pair0 = createPair("UNIQUE-ID", "ANDELA-FELLOW");
    Pair pair1 = createPair("TYPES", "OMOTOSO");
    Pair pair2 = createPair("COMMON-NAME", "FEMALE");
    Pair pair3 = createPair("ATOM-MAPPINGS", "ANDELA");
    Pair pair4 = createPair("IN-PATHWAY", "10");

    ReactionDBWriter reactionDBWriter;
    Reaction reaction;

    public Pair<String, ArrayList<String>> createPair(String key, String value) {
        ArrayList<String> values = new ArrayList<>();
        values.add(value);
        return new Pair<>(key, values);
    }

    public void populatePairList(){
        reaction.add(pair0);
        reaction.add(pair1);
        reaction.add(pair2);
        reaction.add(pair3);
        reaction.add(pair4);
    }

    @Before
    public void setUp() throws Exception {
        reactionDBWriter = new ReactionDBWriter();
        reaction = new Reaction();
        populatePairList();
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testConnectToReactionDatabase() throws Exception {
        reactionDBWriter.connectToReactionDatabase();
        assertTrue(reactionDBWriter.isConnected());
    }

    @Test
    public void testCreateReactionTable() throws Exception {
        testConnectToReactionDatabase();
        reactionDBWriter.createReactionTable();
    }

    @Test
    public void testWriteReactionToDatabase() throws Exception {
        testConnectToReactionDatabase();
        testCreateReactionTable();
        reactionDBWriter.writeReactionToDatabase(reaction);
        reactionDBWriter.disconnect();
        assertFalse(reactionDBWriter.isConnected());
    }
}