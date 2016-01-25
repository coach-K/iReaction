package pair;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PairTest {

    Pair pair;

    @Before
    public void setUp() throws Exception {
        pair = new Pair<String, String>("Name", "Ogunde Kehinde");
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testGetKey() throws Exception {
        assertEquals(pair.getKey(), "Name");
    }

    @Test
    public void testGetValue() throws Exception {
        assertEquals(pair.getValue(), "Ogunde Kehinde");
    }
}