package Reaction;

import Constant.FilePath;
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
        File file = new File(FilePath.REACT_FILE_PATH.toString());
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