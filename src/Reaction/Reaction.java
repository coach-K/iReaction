package reaction;


import pair.Pair;
import pair.PairList;

import java.util.ArrayList;

/**
 * This is a defined pair List class, it extends PairList
 * Holds to holds a list of pairs
 *
 * @author OGUNDE KEHINDE
 * @literal <K> key of element}
 * {@literal <V> value of element}
 * <p/>
 * <p>This class is a member of the
 * <a href="{@docRoot}/../src/reaction/reaction/index.html">
 * Java Collections Framework</a>.
 * @see PairList
 * @since 7.1
 */
public class Reaction extends PairList<String, ArrayList<String>> {

    /**
     * Make reference to super constructor
     */
    public Reaction() {
        super();
    }

    /**
     * Returns element value of a pair where key equals UNIQUE-ID
     * More formally, returns {@code <tt>e.getValue.toString()</tt>}
     * if and only if {@code pair.getKey().equals(UNIQUEID)}
     *
     * @return element value of a pair where key equals UNIQUE-ID
     * @see PairList#get
     */
    public String getUniqueId() {
        Pair reactant = get("UNIQUE-ID");
        return reactant != null ? reactant.getValue().toString() : null;
    }
}
