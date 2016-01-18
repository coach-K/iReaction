package Reaction;

import Pair.Pair;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class ReactionTest {

    Reaction reaction;

    Pair pair0 = createPair("UNIQUE-ID", "ANDELA-FELLOW");
    Pair pair1 = createPair("GRACE", "OMOTOSO");
    Pair pair2 = createPair("GENDER", "FEMALE");
    Pair pair3 = createPair("SCHOOL", "ANDELA");
    Pair pair4 = createPair("CLASS", "10");

    @Before
    public void setUp() throws Exception {
        reaction = new Reaction();
    }

    @After
    public void tearDown() throws Exception {

    }

    public Pair<String, ArrayList<String>> createPair(String key, String value){
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

    @Test
    public void testGetUniqueId() throws Exception {
        assertEquals(reaction.size(), 0);
        populatePairList();
        assertNotNull(reaction);
        assertEquals(reaction.getUniqueId(), "[ANDELA-FELLOW]");
    }
}