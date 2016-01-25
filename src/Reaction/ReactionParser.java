package reaction;

import pair.Pair;
import parser.PairFileParser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * This class uses a PairFileParser
 * to read the content of a pair file into a reaction object.
 * returns a reaction element.
 * This class is a member of the
 * <a href="{@docRoot}/../src/parser/PairFileParser/index.html">
 * Java Reader Framework</a>.
 *
 * @author OGUNDE KEHINDE
 * @see Pair
 * @see parser.PairFileParser
 * @since 1.7
 */
public class ReactionParser extends PairFileParser {

    /**
     * Create a reaction element
     */
    private Reaction reaction;

    /**
     * Construct and accept a file to be parsed
     * throws a FileNotFoundException if file this not exist.
     *
     * @param file to be parsed
     * @throws FileNotFoundException if file does not exist
     */
    public ReactionParser(File file) throws FileNotFoundException {
        super(file);
        super.endOfBlock('/', '/');
        reaction = new Reaction();
    }

    /**
     * Returns the next #reaction element, or <code>null</code> if the end of the
     * stream is reached.
     *
     * @return the next #reaction element, or <code>null</code> if the end of the
     * stream is reached.
     * @throws IOException if this input stream has been closed by
     *                     invoking its {@link parser.PairFileParser#close()} method,
     *                     or an I/O error occurs.
     * @see java.io.FilterInputStream#in
     */
    public synchronized Reaction readReaction() throws IOException {
        Pair pair;
        Reaction reaction1 = null;

        while ((pair = readPair('-')) != null) {
            if (isEndOfBlock()) {
                reaction1 = reaction;
                reaction = new Reaction();
                break;
            }

            reaction.add(pair);
        }

        return reaction1;
    }
}
