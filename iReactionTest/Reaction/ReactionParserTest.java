package Reaction;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class ReactionParserTest {

    ReactionParser reactionParser;

    @Before
    public void setUp() throws Exception {
        String DESKTOP_PATH = System.getProperty("user.dir");
        String FILE_NAME = DESKTOP_PATH + "\\assets\\react.txt";

        File file = new File(FILE_NAME);
        reactionParser = new ReactionParser(file);
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testReadReactant() throws Exception {
        Reaction reaction;
        while ((reaction = reactionParser.readReaction()) != null) {
            if (reactionParser.isEndOfBlock()) {
                break;
            }
        }
        assertNotNull(reaction);
        assertEquals(reaction.getUniqueId(), "[RXN0-6375]");
    }
}