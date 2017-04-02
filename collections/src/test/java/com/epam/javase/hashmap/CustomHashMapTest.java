package com.epam.javase.hashmap;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;

import java.util.*;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by aivanov on 3/24/2017.
 */

@FixMethodOrder
public class CustomHashMapTest {

    private Map<Integer, String> m;
    private Set<Integer> keySet;
    private final Integer key = new Integer(1000);
    private final String value = "abc";;
    private final Integer[] values = {100, 222, 3123, 122, 987, 0};


    @Before
    public void init() {
        m = new CustomHashMap<>();
        keySet = m.keySet();
    }

    @Test
    public void testThatWeCanCreate() {
        assertThat(m, is(notNullValue()));
    }

    @Test
    public void testThatNewMapIsEmpty() {
        assertThat(m.isEmpty(), is(true));
    }

    @Test
    public void testIfNewMapContainsNoObjects() {
        assertThat(m.containsKey(new Integer(1)), is(false));
    }

    @Test
    public void testThatWeCanPutKeyValuePairAndCanCheckIt() {
        m.put(new Integer(3), "abc");
        assertThat(m.containsKey(3), is(true));
    }

    @Test(expected = NullPointerException.class)
    public void testThatWeCantPutNullKey() {
        m.put(null, "abc");
    }

    @Test
    public void testThatWeCanPutNullValue() {
        Integer key = new Integer(10);
        m.put(key, null);
        assertThat(m.get(new Integer(9)), is(nullValue()));
    }

    @Test
    public void testIfItPossibleToPutTheSameKeyWithDifferentValue() {
        String oldValue = "abc";
        String newValue = "cba";
        m.put(key, oldValue);
        m.put(key, newValue);

        assertThat(m.get(key), is(newValue));
    }

    @Test
    public void testIfPutMethodReturnOldValueWhenPutTheSameKeyWithDifferentValue() {
        String oldValue = "abc";
        String newValue = "cba";
        m.put(key, oldValue);

        assertThat(m.put(key, newValue), is(oldValue));
    }

    @Test
    public void testIfEmptyReturnFalseThenMapIsntEmpty() {
        m.put(key, value);
        assertFalse(m.isEmpty());
    }

    @Test(expected = NullPointerException.class)
    public void testIfGetThrowsExceptionIfKeyIsNull() {
        m.get(null);
    }

    @Ignore
    @Test(expected = ClassCastException.class)
    public void testIfGetThrowsExceptionIfWeSpecifyWrongKeyClass() {
        m.get(new CustomHashMapTest());
    }

    @Test
    public void testIfGetReturnNullIfTheKeyWasntFound() {
        assertThat(m.get(new Integer(1000)), is(nullValue()));
    }


    @Test
    public void testIfGetReturnExpectedValueWhenMapContainsKey() {
        Integer key = 10;
        String value = "abc";
        m.put(key, value);

        assertThat(m.get(key), is(value));
    }

    @Test
    public void testIfGetReturnNullWhenMapContainsKeyAndValueEqualsNull() {
        Integer key = 10;
        m.put(key, null);

        assertThat(m.get(key), is(nullValue()));
    }

    @Ignore
    @Test(expected = OutOfMemoryError.class)
    public void testThatMapHaveInfiniteCapacity() {
    }

    @Test
    public void testThatMapCanContainsKeysWithSameHashCode() {
    }

    @Test(expected = NullPointerException.class)
    public void testThatContainsKeyMethodThrowsExceptionOnNullKey() {
        m.containsKey(null);
    }

    @Ignore
    @Test(expected = ClassCastException.class)
    public void testIfContainsKeyMethodThrowsExceptionOnWrongKeyClass() {

        Integer key = new Integer(16);
    }

    @Test
    public void testContainsValueMethodWorksProperly() {
        m.put(key, value);
        assertTrue(m.containsValue(value));
    }

    @Test
    public void testContainsValueMethodWorksProperlyOnNullInputValue() {
     m.put(key, null);
     assertTrue(m.containsValue(null));
    }

    @Ignore
    @Test(expected = ClassCastException.class)
    public void testIfContainsValueMethodThrowsExceptionOnWrongInputValueClass() {
    }

    @Test
    public void testIfNewMapSizeEqualsZero() {
        assertThat(m.size(), is(0));
    }

    @Test
    public void testMapSizeAfterAddingTenEqualsKeys() {
        for (int i = 0; i < 10; i++) {
            m.put(key, value + i);
        }
        assertThat(m.size(), is(1));
    }

    @Test
    public void testMapSizeAfterAddingTenDifferentKeys() {
        int count = 10;
        for (int i = 0; i < count; i++) {
            m.put(i, value);
        }
        assertThat(m.size(), is(count));
    }

    @Ignore
    @Test
    public void testMapSizeAfterAddingIntegerMaxValueKeys() {
        for (int i = Integer.MAX_VALUE; i > 0; i--) {
            m.put(i, value);
        }
        assertThat(m.size(), is(Integer.MAX_VALUE));
    }

    @Ignore
    @Test
    public void testMapSizeAfterAddingIntegerMaxValuePlusOneKeys() {
        CustomHashMap<String, String> map = new CustomHashMap<>();

        for (long i = Integer.MAX_VALUE + 1; i > 0; i--) {
            map.put("" + i, value);
        }
        assertThat(map.size(), is(Integer.MAX_VALUE));
    }

    @Test(expected = NullPointerException.class)
    public void testIfRemoveThrowsExceptionIfWeTryRemoveNullKey() {
        m.remove(null);
    }

    @Test
    public void testIfRemoveWorkAsExpected() {
        m.put(key, value);
        assertThat(m.remove(key), is(value));
    }

    @Test
    public void testIfRemoveTrulyRemoveEntry() {
        m.put(key, value);
        m.remove(key);
        assertFalse(m.containsKey(key));
    }

    @Test
    public void testIfEmptyMapReturnEmptyKeySet() {
        assertThat(m.keySet().size(), is(0));
    }

    @Test
    public void testIfKeySetAndMapContainSameValues() {
        for (Integer i : values) {
            m.put(i, null);
        }

        for (Integer i : keySet) {
            assertTrue(m.containsKey(i));
        }
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testIfKeySetThrowsExceptionOnAddMethodInvocation() {
        keySet.add(new Integer(1));
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testIfKeySetThrowsExceptionOnAddAllMethodInvocation() {
        keySet.addAll(Collections.singletonList(key));
    }

    @Test
    public void testIfKeySetIteratorRemoveMethodTrulyRemoveElementFromMap() {

        for (Integer i : values) {
            m.put(i, null);
        }

        Iterator<Integer> iterator = keySet.iterator();
        while (iterator.hasNext()) {
            iterator.next();
            iterator.remove();
        }
        assertTrue(m.isEmpty());
    }

    @Test
    public void testIfKeySetRemoveMethodTrulyRemoveElementFromMap() {
        for (Integer i : values) {
            m.put(key, null);
        }

        for (Integer i : values) {
            keySet.remove(key);
            assertFalse(m.containsKey(key));
        }
    }

    @Test(expected = NullPointerException.class)
    public void testIfKeySetRemoveMethodThrowsExceptionOnNullKey() {
        keySet.remove(null);
    }

    @Test
    public void testIfKeySetRemoveMethodReturnTrueOnSuccess() {
        m.put(key, null);
        assertTrue(keySet.remove(key));
    }

    @Test
    public void testIfKeySetRemoveMethodReturnFalseIfMapDoesntContainSuchKey() {
        assertFalse(keySet.remove(key));
    }

    @Test
    public void testIfKeySetRemoveAllMethodTrulyRemoveElementsFromMap() {

        for (Integer i : values) {
            m.put(i, null);
        }

        keySet.removeAll(Arrays.asList(values));

        for (Integer i : values) {
            assertFalse(m.containsKey(i));
        }
    }

    @Test(expected = NullPointerException.class)
    public void testIfKeySetRemoveAllMethodThrowsExceptionIfKeyIsNull() {
        keySet.removeAll(null);
    }

    @Test
    public void testIfKeySetRemoveAllMethodReturnTrueOnSuccess() {
        m.put(key, null);
        assertTrue(keySet.removeAll(Collections.singleton(key)));
    }

    @Test
    public void testIfKeySetRemoveAllMethodReturnFalseIfMapDoesntContainSuchKey() {
        assertFalse(keySet.remove(key));
    }

    @Test
    public void testIfKeySetRetainAllMethodTrulyRemoveElementsFromMap() {

        int[] src = {1, 2, 3, 4, 5};
        int[] remove = {1, 2, 3};
        int[] retain = {4, 5};

        for (Integer i : src) {
            m.put(i, null);
        }

        keySet.retainAll(Arrays.asList(retain));

        for (Integer i : remove) {
            assertFalse(m.containsKey(i));
        }
    }

    @Test
    public void testIfKeySetClearMethodTrulyClearMap() {
        for (Integer i : values) {
            m.put(i, null);
        }

        keySet.clear();
        assertTrue(m.isEmpty());
    }

    @Test
    public void testIfHashMapClearedByKeySetMethodDoesntContainElements() {
        for (Integer i : values) {
            m.put(i, null);
        }

        keySet.clear();
        for (Integer i : values) {
            assertFalse(m.containsKey(i));
        }
    }

    @Test
    public void testIfHashMapClearedByClearMethodDoesntContainElements() {
        for (Integer i : values) {
            m.put(i, null);
        }

        m.clear();
        for (Integer i : values) {
            assertFalse(m.containsKey(i));
        }
    }

    @Test
    public void testIfKeySetSizeReturnZeroOnEmptyMap() {
        assertThat(keySet.size(), is(0));
    }

    @Test
    public void testIfKeySetSizeEqualsToMapSize() {
        for (Integer i : values) {
            m.put(i, null);
        }

        assertThat(keySet.size(), is(m.size()));
    }

}