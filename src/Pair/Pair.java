package Pair;

/**
 * @author OGUNDE KEHINDE
 * This class is a generics class with two types
 * {@literal} <K> key of element
 * {@literal} <V> value of element
 * <b>An Example code</b>
 * {@code} Pair<String, String> pair = new Pair<>("Hello", "World");
 */
public class Pair <K, V> {
    public static final String DEFAULT = "";
    private K key;
    private V value;

    /**
     * Accepts two parameter of types
     * {@literal} <K>
     * {@literal} <V>
     * @param key type of element
     * @param value value of element
     */
    public Pair(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public K getKey() {
        return key;
    }

    public void setKey(K key) {
        this.key = key;
    }

    public V getValue() {
        return value;
    }

    public void setValue(V value) {
        this.value = value;
    }
}
