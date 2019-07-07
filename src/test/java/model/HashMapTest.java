package model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class HashMapTest {
    private HashMap hashMap = new HashMap(16);

    @Before
    public void hashMapInit() {
        for (int i = 0; i < 10; i++) {
            hashMap.put(i * i, i);
        }
    }

    @Test
    public void getShouldReturnNull() {
        Long[] expecteds = new Long[]{null, null, null, null, null};
        Long[] results = new Long[]{
                hashMap.get(3), hashMap.get(5), hashMap.get(33),
                hashMap.get(44), hashMap.get(1000)
        };

        assertArrayEquals(expecteds, results);
    }

    @Test
    public void getShouldReturnCorrectValue() {
        Long result = hashMap.get(36);
        assertEquals((Long) 6L, result);
    }

    @Test
    public void getShouldReturnAllInputCorrect() {
        long[] expectedArray = new long[10];
        for (int i = 0; i < 10; i++) {
            expectedArray[i] = i;
        }

        long[] resultArray = new long[10];
        for (int i = 0; i < 10; i++) {
            resultArray[i] = hashMap.get(i * i);
        }

        assertArrayEquals(expectedArray, resultArray);
    }

    @Test
    public void shouldReturnCorrectSize(){
        int expected = 11;
        hashMap.put(121, 11);
        int result = hashMap.size();

        assertEquals(expected, result);
    }

    @Test
    public void shouldChangeSize(){
        int old = 10;
        hashMap.put(121, 11);
        int result = hashMap.size();

        assertNotEquals(old, result);
    }
}