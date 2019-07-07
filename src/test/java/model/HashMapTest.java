package model;

import org.junit.Test;

import static org.junit.Assert.*;

public class HashMapTest {
    private HashMap hashMap = new HashMap(16);

    @Test
    public void getShouldReturnNull() {
        for (int i = 0; i < 10; i++){
            hashMap.put(i*i, i);
        }

        Long result = hashMap.get(3);
        assertEquals(null, result);
    }
}