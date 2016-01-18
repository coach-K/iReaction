package Reaction;


import Pair.Pair;
import Pair.PairList;

import java.util.ArrayList;

/**
 * This is a defined Pair List class, it extends PairList
 * Holds to holds a list of pairs
 * @literal <K> key of element}
 * {@literal <V> value of element}
 *
 * <p>This class is a member of the
 * <a href="{@docRoot}/../src/Reaction/Reaction/index.html">
 * Java Collections Framework</a>.
 *
 * @author OGUNDE KEHINDE
 * @see     PairList
 * @since   7.1
 */
public class Reaction  extends PairList<String, ArrayList<String>> {

    /**
     * Make reference to super constructor
     */
    public Reaction(){
        super();
    }

    /**
     * Returns element value of a pair where key equals UNIQUE-ID
     * More formally, returns {@code <tt>e.getValue.toString()</tt>}
     * if and only if {@code pair.getKey().equals(UNIQUEID)}
     *
     * @see PairList#get
     *
     * @return element value of a pair where key equals UNIQUE-ID
     */
    public String getUniqueId(){
        Pair reactant = get("UNIQUE-ID");
        return reactant != null ? reactant.getValue().toString() : null;
    }
}
