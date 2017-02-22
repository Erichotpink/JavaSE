package com.epam.java.se;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.*;

/**
 * Created by aivanov on 2/21/2017.
 */
public class IntArrayListTest {

    @Test
    public void sort() throws Exception {
        final int[] ints = {12, 0, -13, 666, 2, 56, 56, 56, 120, -1, 1, 0, Integer.MAX_VALUE, Integer.MIN_VALUE};
        final int[] expected = Arrays.copyOf(ints, ints.length);
        Arrays.sort(expected);

        final IntArrayList list = new IntArrayList(ints);

        list.sort();
        for (int i = 0; i < expected.length; i++) {
            assertEquals("i = " + i, expected[i], list.get(i));
        }

    }

    @Test
    public void binarySearch() throws Exception {

        final int[] ints = {-14, -13, -1, 0, 0, 1, 2, 12, 56, 56, 56, 120, 666, 4444};

        final IntArrayList list = new IntArrayList(ints);

        checkIndexForValuesOutsideOfArray(list);
        checkIndexForValuesInsideOfArray(list);
    }

    private void checkIndexForValuesOutsideOfArray(IntArrayList list) {
        final int maxValue = list.get(list.getSize() - 1) + 1;
        final int minValue = list.get(0) - 1;

        assertEquals(-1, list.binarySearch(minValue));
        assertEquals(-list.getSize() - 1, list.binarySearch(maxValue));
        assertEquals(-3, list.binarySearch(-5));
    }

    private void checkIndexForValuesInsideOfArray(IntArrayList list) {

        final int maxValue = list.get(list.getSize() - 1);
        final int minValue = list.get(0);

        assertEquals(0, list.binarySearch(minValue));
        assertEquals(list.getSize() - 1, list.binarySearch(maxValue));
        assertTrue(list.binarySearch(0) >= 0);
    }

}