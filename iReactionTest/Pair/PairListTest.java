package Pair;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;

import static org.junit.Assert.*;

public class PairListTest {

    PairList<String, String> pairs;
    PairList<String, String> pairs2;
    PairList<String, String> freshPairs;
    Pair<String, String> pair1 = new Pair<>("TOSIN", "ADESANYA");
    Pair<String, String> pair2 = new Pair<>("GRACE", "BUKOLA");
    Pair<String, String> pair3 = new Pair<>("DANNY", "JAX");
    Pair<String, String> pair4 = new Pair<>("OBIOMA", "OBIM");

    @Before
    public void setUp() throws Exception {
        pairs = new PairList<String, String>();
        pairs2 = new PairList<String, String>();
        freshPairs = new PairList<String, String>();
    }

    @After
    public void tearDown() throws Exception {

    }

    public void addPairs(){
        pairs.add(pair1);
        pairs.add(pair2);
        pairs.add(pair3);
    }

    public void addPairs2(){
        pairs2.add(pair1);
        pairs2.add(pair2);
    }

    public void addFreshPairs(){
        Pair<String, String> pair5 = new Pair<>("TOSIN", "ADESANYA");
        Pair<String, String> pair6 = new Pair<>("DANNY", "JAX");
        freshPairs.add(pair5);
        freshPairs.add(pair6);
    }

    @Test
    public void testGet() throws Exception {
        Pair pair = null;
        try {
            pair = pairs.get(0);
        } catch (IndexOutOfBoundsException e){
            assertNull(pair);
        }

        addPairs();
        assertNotNull(pairs.get(0));
    }

    @Test
    public void testGet1() throws Exception {
       assertNull(pairs.get("TOSIN"));
        addPairs();
        assertEquals(pairs.get("TOSIN").getValue(), pair1.getValue());
    }

    @Test
    public void testSize() throws Exception {
        assertEquals(pairs.size(), 0);
        addPairs();
        assertEquals(pairs.size(), 3);
    }

    @Test
    public void testIsEmpty() throws Exception {
        assertTrue(pairs.isEmpty());
        addPairs();
        assertFalse(pairs.isEmpty());
    }

    @Test
    public void testContains() throws Exception {
        assertFalse(pairs.contains(pair1));
        addPairs();
        assertTrue(pairs.contains(pair1));
        Pair pair = new Pair<>("TOSIN", "ADESANYA");
        assertFalse(pairs.contains(pair));
    }

    @Test
    public void testContainsPair() throws Exception {
        assertFalse(pairs.containsPair(pair1));
        addPairs();
        assertTrue(pairs.containsPair(pair1));
        Pair pair = new Pair<>("TOSIN", "ADESANYA");
        assertTrue(pairs.containsPair(pair));
    }

    @Test
    public void testIterator() throws Exception {
        Iterator<Pair<String, String>> iterator = pairs.iterator();
        while (iterator.hasNext()){
            assertEquals(iterator.next().getValue(), pair1.getValue());
            break;
        }
    }

    @Test
    public void testToArray() throws Exception {
        assertEquals(pairs.toArray().length, 0);
        Object[] array = {pair1, pair2, pair3};
        addPairs();
        assertEquals(array, pairs.toArray());
    }

    @Test
    public void testToArray1() throws Exception {
        Object[] array = pairs.toArray(new Pair[3]);
        assertEquals(array.length, 3);
        assertNull(array[0]);
        addPairs();
        array = pairs.toArray(new Pair[3]);
        assertNotNull(array[0]);
    }

    @Test
    public void testAdd() throws Exception {
        assertTrue(pairs.isEmpty());
        assertTrue(pairs.add(pair1));
        assertFalse(pairs.add(pair1));
        assertFalse(pairs.isEmpty());
    }

    @Test
    public void testRemove() throws Exception {
        assertFalse(pairs.remove(pair1));
        addPairs();
        assertTrue(pairs.remove(pair1));
    }

    @Test
    public void testContainsAll() throws Exception {
        addFreshPairs();
        assertFalse(pairs.containsAll(freshPairs));
        addPairs();
        assertFalse(pairs.containsAll(freshPairs));
        addPairs2();
        assertTrue(pairs.containsAll(pairs2));
    }

    @Test
    public void testContainsAllPairs() throws Exception {
        addFreshPairs();
        assertFalse(pairs.containsAllPair(freshPairs));
        addPairs();
        assertTrue(pairs.containsAllPair(freshPairs));
    }

    @Test
    public void testAddAll() throws Exception {
        assertTrue(pairs.isEmpty());
        addFreshPairs();
        assertTrue(pairs.addAll(freshPairs));
        assertFalse(pairs.isEmpty());
    }

    @Test
    public void testRemoveAll() throws Exception {
        assertEquals(pairs.size(), 0);
        addPairs();
        assertEquals(pairs.size(), 3);
        addPairs2();
        pairs.removeAll(pairs2);
        assertEquals(pairs.size(), 1);
    }

    @Test
    public void testRetainAll() throws Exception {
        assertEquals(pairs.size(), 0);
        addPairs();
        assertEquals(pairs.size(), 3);
        addPairs2();
        pairs.retainAll(pairs2);
        assertEquals(pairs.size(), 2);
    }

    @Test
    public void testClear() throws Exception {
        assertEquals(pairs.size(), 0);
        addPairs();
        assertEquals(pairs.size(), 3);
        pairs.clear();
        assertEquals(pairs.size(), 0);
    }
}