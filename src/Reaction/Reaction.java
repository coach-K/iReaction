package Reaction;


import Pair.Pair;
import Pair.PairList;

import java.util.ArrayList;

public class Reaction  extends PairList<String, ArrayList<String>> {

    public Reaction(){
        super();
    }

    public String getUniqueId(){
        Pair reactant = get("UNIQUE-ID");
        return reactant != null ? reactant.getValue().toString() : null;
    }
}
