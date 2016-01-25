package parser;

import pair.Pair;

import java.io.*;

/**
 * This class uses a buffered input stream
 * to read the content of a pair file into a pair buffer.
 * returns a pair element.
 * This class is a member of the
 * <a href="{@docRoot}/../src/parser/PairFileParser/index.html">
 * Java Reader Framework</a>.
 *
 * @author OGUNDE KEHINDE
 * @see Pair
 * @see java.util.List
 * @see java.util.ArrayList
 * @see java.io.BufferedInputStream
 * @since 1.7
 */
public class PairFileParser {

    /**
     * Creates BufferedInputStream
     */
    private BufferedInputStream bufferedInputStream;

    /**
     * Creates FileInputStream
     */
    private FileInputStream fileInputStream;

    /**
     * Stores the content of a file as key and value
     *
     * @see parser.PairBuffer#appendKey
     * @see parser.PairBuffer#appendValue
     */
    private PairBuffer pairBuffer;

    /**
     * Element determine the end of a block in a file.
     * More formally, if and only if <tt>e</tt> is not null such that
     * <p/>
     * {@code e != null}
     */
    private String END_OF_BLOCK;

    /**
     * Stores current string returned by buffered input stream.
     */
    private StringBuffer currStringBuffer;

    /**
     * stores current character returned by buffered input stream
     */
    private char currentChar = '\0';

    /**
     * Set to <tt>true</tt> if and only if {@literal #}
     * is returned by buffered input stream.
     */
    private boolean comment;

    /**
     * Set to <tt>true</tt> if and only if element returned is not equals {@literal #}
     */
    private boolean isValue;

    /**
     * Set to <tt>true</tt> if and ony if element returned
     * is equals to #END_OF_BLOCK
     */
    private boolean endOfBlock;

    /**
     * Accept a file to be parsed and construct a new PairFileParer element.
     *
     * @param file to be parsed
     * @throws FileNotFoundException if and only if file is not found
     */
    public PairFileParser(File file) throws FileNotFoundException {
        if (file.exists()) {
            fileInputStream = new FileInputStream(file);
            this.bufferedInputStream = new BufferedInputStream(fileInputStream);
            this.pairBuffer = new PairBuffer();
            currStringBuffer = new StringBuffer();
        } else {
            throw new FileNotFoundException("File was not found");
        }
    }

    /**
     * Reads the content of a file by character
     * Returns next pair element
     * Uses delimiter to determine the key and value of the read content
     * Throws IOException if file cannot be read
     *
     * @param delimiter to determines the key and value
     * @return next pair element
     * @throws IOException if file cannot be read.
     */
    public synchronized Pair readPair(char delimiter) throws IOException {
        Pair pair = null;

        while (this.bufferedInputStream.available() > 0) {
            char previousChar = currentChar;
            currentChar = (char) read();

            String pnChar = String.valueOf(previousChar) + String.valueOf(currentChar);

            if (currentChar == '\n') {
                isValue = false;
                endOfBlock = false;
                pair = pairBuffer.toPair();
                break;
            }

            if (this.END_OF_BLOCK == null) {
                loadKeyValue(delimiter, currentChar, previousChar);
            } else {
                if (this.END_OF_BLOCK.equals(pnChar)) {
                    endOfBlock = true;
                    pair = pairBuffer.toPair();

                    currentChar = (char) read();
                    currentChar = (char) read();

                    break;
                }

                loadKeyValue(delimiter, currentChar, previousChar);
            }
        }
        return pair;
    }

    /**
     * Read the content of a file by character
     * Returns a character element.
     * Throws and IOException if the file cannot be read.
     *
     * @return a character element.
     * @throws IOException if the file cannot be read
     */
    public synchronized int read() throws IOException {
        char previous;
        char current = 0;

        while (true) {
            previous = current;
            current = (char) bufferedInputStream.read();

            if (previous == '\n') {
                comment = false;
            }

            if (current == '#' || comment) {
                comment = true;
                continue;
            }

            return current;
        }
    }

    /**
     * Read the content of a file by line
     * Returns a string element.
     * Throws and IOException if the file cannot be read.
     *
     * @return a string element.
     * @throws IOException if the file cannot be read
     */
    public synchronized String readLine() throws IOException {
        char previous;
        char current = 0;

        while (true) {
            previous = current;
            current = (char) bufferedInputStream.read();

            if (previous == '\n') {
                comment = false;
            }

            if (current == '#' || comment) {
                comment = true;
                continue;
            }

            if (current == '\n') {
                currStringBuffer.trimToSize();
            } else {
                currStringBuffer.append(current);
                continue;
            }
            return currStringBuffer.toString().trim();
        }
    }

    /**
     * Set characters to determine the end of a block
     * More formally, This invokes if and only if file is structured in blocks
     * <p/>
     * {@literal
     * <tt>pair file example</tt>
     * <p/>
     * NAME = JOHN DOE
     * POSITION = TRAINER
     * ---------------
     * NAME = JANE DOE
     * POSITION = FELOW
     * --------------
     * <p/>
     * The hyphens (--) signifies the end of block.
     * }
     *
     * @param EOB1 character to determine end of a block
     * @param EOB2 character to determine end of a block
     */
    public void endOfBlock(char EOB1, char EOB2) {
        this.END_OF_BLOCK = String.valueOf(EOB1) + String.valueOf(EOB2);
    }

    /**
     * Returns a boolean element.
     *
     * @return Returns a boolean element.
     */
    public boolean isEndOfBlock() {
        return endOfBlock;
    }

    /**
     * Close BufferedInputStream and FileInputStream
     *
     * @throws IOException if BufferedInputStream and FileInputStream was not closed
     */
    public void close() throws IOException {
        if (bufferedInputStream != null && fileInputStream != null) {
            bufferedInputStream.close();
            fileInputStream.close();
        }
    }

    /**
     * Populates the pair buffer element with file content based on this delimiter
     * in order to create a pair element
     *
     * @param delimiter    determines the key and value to create a pair element
     * @param currentChar  read by buffered input stream
     * @param previousChar read by buffered input stream
     */
    private void loadKeyValue(char delimiter, char currentChar, char previousChar) {
        String split = delimiter + " ";
        String pnChar = String.valueOf(previousChar) + String.valueOf(currentChar);

        if ((isValue || pnChar.equals(split))) {
            isValue = true;
            pairBuffer.appendValue(currentChar);
        } else {
            pairBuffer.appendKey(previousChar);
        }
    }
}
