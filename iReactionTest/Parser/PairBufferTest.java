package Parser;

import Pair.Pair;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class PairBufferTest {

    Pair<String, ArrayList<String>> pair;
    PairBuffer pairBuffer;

    @Before
    public void setUp() throws Exception {
        pair = new Pair<>(Pair.DEFAULT_KEY, Pair.DEFAULT_VALUE);
        pairBuffer = new PairBuffer();
    }

    @After
    public void tearDown() throws Exception {

    }

    public void setKey(String key){
        for (int i = 0, s = key.length(); i < s; i++){
            pairBuffer.appendKey(key.charAt(i));
        }
    }

    public void setValue(String value){
        for (int i = 0, s = value.length(); i < s; i++){
            pairBuffer.appendValue(value.charAt(i));
        }
    }

    @Test
    public void testToPair() throws Exception {
        testAppendKey();
        testAppendValue();
        pair = pairBuffer.toPair();
        assertEquals(pair.getKey(), "TOSIN");
        assertEquals(pair.getValue().size(), 1);
        assertEquals(pair.getValue().get(0), "ADESANYA");

        setKey("TOSIN");
        setValue("DANNY JAX");
        assertEquals(pair.getKey(), "TOSIN");
        pair = pairBuffer.toPair();
        assertEquals(pair.getValue().size(), 2);
        assertEquals(pair.getValue().get(0), "ADESANYA");
        assertEquals(pair.getValue().get(1), "DANNY JAX");
    }

    @Test
    public void testAppendKey() throws Exception {
        assertTrue(pairBuffer.toKey().isEmpty());
        setKey("TOSIN");
        assertFalse(pairBuffer.toKey().isEmpty());
        assertEquals(pairBuffer.toKey(), "TOSIN");
    }

    @Test
    public void testAppendValue() throws Exception {
        assertTrue(pairBuffer.toValue().isEmpty());
        setValue("ADESANYA");
        assertFalse(pairBuffer.toValue().isEmpty());
        assertEquals(pairBuffer.toValue(), "ADESANYA");
    }

    @Test
    public void testToKey() throws Exception {
        testAppendKey();
    }

    @Test
    public void testToValue() throws Exception {
        testAppendValue();
    }
}