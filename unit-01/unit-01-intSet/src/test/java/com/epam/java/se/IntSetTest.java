package com.epam.java.se;

import org.junit.Ignore;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by Andrey Ivanov on 2/1/2017.
 */
public class IntSetTest {

    @Test
    public void add() {
        final IntSet set = new IntSet();


        assertFalse(set.contains(Integer.MAX_VALUE));
        set.add(Integer.MAX_VALUE);
        assertTrue(set.contains(Integer.MAX_VALUE));

        assertFalse(set.contains(0));
        assertFalse(set.contains(Integer.MAX_VALUE - 1));

        assertFalse(set.contains(0));
        set.add(0);
        assertTrue(set.contains(0));
        assertFalse(set.contains(1));

        assertFalse(set.contains(1024));
        set.add(1024);
        assertTrue(set.contains(1024));

        assertFalse(set.contains(1023));
        assertFalse(set.contains(1025));

    }

    @Test
    public void remove() {

        final IntSet set = new IntSet();

        set.remove(Integer.MAX_VALUE);
        set.remove(0);
        set.remove(2045);

        assertFalse(set.contains(Integer.MAX_VALUE));
        assertFalse(set.contains(0));
        assertFalse(set.contains(2045));


        set.add(0);
        set.add(1);
        set.add(11647);
        set.add(11648);
        set.add(11649);
        set.add(Integer.MAX_VALUE);
        set.add(Integer.MAX_VALUE - 1);

        set.remove(Integer.MAX_VALUE);
        set.remove(11648);
        set.remove(0);

        assertTrue(set.contains(1));
        assertTrue(set.contains(11647));
        assertTrue(set.contains(11649));
        assertTrue(set.contains(Integer.MAX_VALUE - 1));

        assertFalse(set.contains(Integer.MAX_VALUE));
        assertFalse(set.contains(0));
        assertFalse(set.contains(11648));

    }

    @Test
    public void contains() {
        final IntSet set = new IntSet();

        assertFalse(set.contains(0));
        assertFalse(set.contains(15648));
        assertFalse(set.contains(Integer.MAX_VALUE));

        set.add(0);
        set.add(1);
        set.add(65);
        set.add(15648);
        set.add(Integer.MAX_VALUE - 64);
        set.add(Integer.MAX_VALUE - 1);
        set.add(Integer.MAX_VALUE);

        assertTrue(set.contains(0));
        assertTrue(set.contains(1));
        assertTrue(set.contains(65));

        assertTrue(set.contains(Integer.MAX_VALUE));
        assertTrue(set.contains(Integer.MAX_VALUE - 64));
        assertTrue(set.contains(Integer.MAX_VALUE - 1));

        assertFalse(set.contains(2));
        assertFalse(set.contains(64));
        assertFalse(set.contains(66));

        assertFalse(set.contains(Integer.MAX_VALUE - 65));
        assertFalse(set.contains(Integer.MAX_VALUE - 63));
    }

    @Test
    public void union() {

        IntSet set1 = new IntSet();
        IntSet set2 = new IntSet();

        IntSet set3 = set1.union(set1);

        assertFalse(set3.contains(0));
        assertFalse(set3.contains(Integer.MAX_VALUE));

        set3 =  set1.union(set2);

        assertFalse(set3.contains(0));
        assertFalse(set3.contains(Integer.MAX_VALUE));

        set1.add(0);
        set1.add(14444);
        set1.add(Integer.MAX_VALUE);

        set2.add(10);
        set2.add(111222);
        set2.add(Integer.MAX_VALUE - 65);

        set3 = set1.union(set2);

        assertTrue(set3.contains(0));
        assertTrue(set3.contains(14444));
        assertTrue(set3.contains(Integer.MAX_VALUE));
        assertTrue(set3.contains(10));
        assertTrue(set3.contains(111222));
        assertTrue(set3.contains(Integer.MAX_VALUE - 65));

        assertFalse(set3.contains(123));
        assertFalse(set3.contains(1));
        assertFalse(set3.contains(14443));
        assertFalse(set3.contains(14445));
        assertFalse(set3.contains(Integer.MAX_VALUE - 1));

        assertTrue(set1.contains(0));
        assertTrue(set1.contains(14444));
        assertTrue(set1.contains(Integer.MAX_VALUE));

        assertFalse(set1.contains(10));
        assertFalse(set1.contains(111222));
        assertFalse(set1.contains(Integer.MAX_VALUE - 65));

        assertTrue(set2.contains(10));
        assertTrue(set2.contains(111222));
        assertTrue(set2.contains(Integer.MAX_VALUE - 65));

        assertFalse(set2.contains(0));
        assertFalse(set2.contains(14444));
        assertFalse(set2.contains(Integer.MAX_VALUE));

    }

    @Test
    public void intersection() {

        IntSet set1 = new IntSet();
        IntSet set2 = new IntSet();

        IntSet set3 = set1.intersection(set1);

        assertFalse(set3.contains(0));
        assertFalse(set3.contains(Integer.MAX_VALUE));

        set1.add(0);
        set1.add(14444);
        set1.add(111222);
        set1.add(Integer.MAX_VALUE);

        set2.add(0);
        set2.add(10);
        set2.add(111222);
        set2.add(Integer.MAX_VALUE - 65);
        set2.add(Integer.MAX_VALUE);

        set3 = set1.intersection(set2);

        assertTrue(set3.contains(0));
        assertTrue(set3.contains(111222));
        assertTrue(set3.contains(Integer.MAX_VALUE));

        assertFalse(set3.contains(14444));
        assertFalse(set3.contains(Integer.MAX_VALUE - 65));


        assertTrue(set1.contains(0));
        assertTrue(set1.contains(14444));
        assertTrue(set1.contains(111222));
        assertTrue(set1.contains(Integer.MAX_VALUE));
        assertFalse(set1.contains(Integer.MAX_VALUE - 65));

        assertTrue(set2.contains(0));
        assertTrue(set2.contains(10));
        assertTrue(set2.contains(111222));
        assertTrue(set2.contains(Integer.MAX_VALUE - 65));
        assertFalse(set2.contains(14444));

    }

    @Test
    public void difference() {

        IntSet set1 = new IntSet();
        IntSet set2 = new IntSet();

        set1.add(0);
        set1.add(1024);
        set1.add(992266);

        set2.add(1024);
        set2.add(4096);
        set2.add(998877);

        IntSet set3 = set1.difference(set2);

        assertTrue(set3.contains(0));
        assertTrue(set3.contains(992266));

        assertFalse(set3.contains(998877));
        assertFalse(set3.contains(4096));


        assertTrue(set1.contains(0));
        assertTrue(set1.contains(1024));
        assertTrue(set1.contains(992266));
        assertFalse(set1.contains(998877));

        assertTrue(set2.contains(1024));
        assertTrue(set2.contains(4096));
        assertTrue(set2.contains(998877));
        assertFalse(set2.contains(0));

    }

    @Test
    public void isSubsetOf() {

        IntSet set1 = new IntSet();
        IntSet set2 = new IntSet();

        assertTrue(set1.isSubsetOf(set1));
        assertTrue(set1.isSubsetOf(set2));

        set1.add(0);
        set1.add(1);
        set1.add(1024);
        set1.add(Integer.MAX_VALUE);

        set2.add(0);
        set2.add(1);
        set2.add(1024);
        set2.add(Integer.MAX_VALUE - 1024);
        set2.add(Integer.MAX_VALUE);

        assertTrue(set1.isSubsetOf(set2));
        assertFalse(set2.isSubsetOf(set1));

        assertTrue(set1.isSubsetOf(set1));
        assertTrue(set2.isSubsetOf(set2));

        assertFalse(set1.contains(Integer.MAX_VALUE - 1024));
        assertTrue(set2.contains(Integer.MAX_VALUE - 1024));
        assertTrue(set1.contains(Integer.MAX_VALUE));

    }

    @Test
    public void addAll() throws Exception {

        IntSet set1 = new IntSet();
        IntSet set2 = new IntSet();

        set1.add(1);
        set1.add(1024);
        set1.add(2048);

        set1.addAll(set1);

        assertTrue(set1.contains(1));
        assertTrue(set1.contains(1024));
        assertTrue(set1.contains(2048));
        assertFalse(set1.contains(2));


        set2.add(0);
        set2.add(2048);
        set2.add(4096);
        set2.add(882266);

        set1.addAll(set2);

        assertTrue(set2.contains(0));
        assertTrue(set2.contains(4096));
        assertTrue(set2.contains(882266));
        assertFalse(set2.contains(1024));

        assertTrue(set1.contains(0));
        assertTrue(set1.contains(1024));
        assertTrue(set1.contains(4096));
        assertTrue(set1.contains(882266));

    }

    @Test
    public void toStringTest() {

        int[] data = {0,1,1024,2147483647};
        IntSet set = new IntSet();
        String str = "";

        for (int x :  data) {
            set.add(x);
            str += x + " ";
        }

        assertEquals(str, set.toString());
    }

    @Test
    public void trimToSize() {

        IntSet set1 = new IntSet();

        set1.add(4);
        set1.add(2048);
        set1.add(Integer.MAX_VALUE);

        set1.remove(Integer.MAX_VALUE);
        set1.contains(Integer.MAX_VALUE);

        set1.trimToSize();

        set1.contains(2048);
        set1.contains(4);
    }
}
