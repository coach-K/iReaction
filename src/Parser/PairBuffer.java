package parser;

import pair.Pair;

import java.util.ArrayList;

/**
 * This is a pair buffer class, this class appends strings as key, and string as values
 * <p/>
 * <p>This class is a member of the
 * <a href="{@docRoot}/../src/parser/pairbuffer/index.html">
 * Java buffer Framework</a>.
 *
 * @author OGUNDE KEHINDE
 * @see Pair
 * @see java.lang.String
 * @see java.util.ArrayList
 * @see java.lang.StringBuffer
 * @since 1.7
 */
public class PairBuffer {

    /**
     * This element stores key and value.
     */
    private Pair<String, ArrayList<String>> pair;

    /**
     * This element store at least one string value
     */
    private ArrayList<String> mValue;

    /**
     * Appends all key string to this element
     */
    private StringBuffer key;

    /**
     * Appends all value string to this element
     */
    private StringBuffer value;

    /**
     * Constructs a new PairBuffer element
     *
     * @see #pair initialize a new pair element
     * @see #mValue initialize a new mValue element
     * @see #resetBuffer initialize key and value element.
     */
    public PairBuffer() {
        pair = new Pair<>(Pair.DEFAULT_KEY, Pair.DEFAULT_VALUE);
        mValue = new ArrayList<>();
        resetBuffer();
    }

    /**
     * Creates a pair element using #key and #value
     * Returns a pair element such that <tt>e</tt>
     * <p/>
     * {@code toKey() == null ? toValue() == null : toPair() }
     *
     * @return a pair element
     */
    public synchronized Pair<String, ArrayList<String>> toPair() {
        if (pair.getKey().equals(toKey())) {
            updateValue(toValue());
            ;
        } else {
            addValue(toKey(), toValue());
        }
        resetBuffer();
        return pair;
    }

    /**
     * Appends the specified character to the sequence.
     *
     * @param currentChar tbe character to append.
     */
    public synchronized void appendKey(char currentChar) {
        key.append(currentChar);
    }

    /**
     * Appends the specified character to the sequence.
     *
     * @param currentChar tbe character to append.
     */
    public synchronized void appendValue(char currentChar) {
        value.append(currentChar);
    }

    /**
     * returns this sequence as string.
     */
    public String toKey() {
        key.trimToSize();
        return key.toString().trim();
    }

    /**
     * returns this sequence as string.
     */
    public String toValue() {
        value.trimToSize();
        return value.toString().trim();
    }

    /**
     * if this key is not null, Add element to bottom of this list.
     *
     * @param value Add value to bottom of this list.
     */
    private void updateValue(String value) {
        mValue = pair.getValue();
        mValue.add(value);
        pair.setValue(mValue);
    }

    /**
     * If this key is null, instantiate this list and add element to this list.
     *
     * @param key   to check if its null
     * @param value value to instantiate and populate.
     */
    private void addValue(String key, String value) {
        mValue = new ArrayList<>();
        mValue.add(value);
        pair = new Pair<>(key, mValue);
    }

    /**
     * Re-instantiate this sequence #key and #value
     */
    private void resetBuffer() {
        value = new StringBuffer();
        key = new StringBuffer();
    }
}
