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
    private Set<Map.Entry<Integer, String>> entrySet;
    private Collection<String> valueSet;
    private final Integer key = new Integer(1000);
    private final String value = "abc";;
    private final Integer[] values = { new Integer(100),
                                       new Integer(222),
                                       new Integer(3123),
                                       new Integer(122),
                                       new Integer(987),
                                       new Integer(0) };


    @Before
    public void init() {
        m = new CustomHashMap<>();
        keySet = m.keySet();
        entrySet = m.entrySet();
        valueSet = m.values();
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
    public void testMapSizeAfterAddingHugeNumberOfKeys() {
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

    @Test
    public void testIfEmptyMapReturnEmptyEntrySet() {
        assertThat(m.entrySet().size(), is(0));
    }

    @Test
    public void testIfEntrySetAndMapContainSameValues() {
        for (Integer i : values) {
            m.put(i, null);
        }

        for (Map.Entry<Integer, String> e : entrySet) {
            assertThat(m.get(e.getKey()), is(e.getValue()));
        }
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testIfEntrySetThrowsExceptionOnAddMethodInvocation() {
        keySet.add(new Integer(1));
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testIfEntrySetThrowsExceptionOnAddAllMethodInvocation() {
        keySet.addAll(Collections.singletonList(key));
    }

    @Test
    public void testIfEntrySetIteratorRemoveMethodTrulyRemoveElementFromMap() {

        for (Integer i : values) {
            m.put(i, null);
        }

        Iterator<Map.Entry<Integer, String>> iterator = entrySet.iterator();
        while (iterator.hasNext()) {
            iterator.next();
            iterator.remove();
        }
        assertTrue(m.isEmpty());
    }

    @Test
    public void testIfEntrySetRemoveMethodTrulyRemoveElementFromMap() {
        for (Integer i : values) {
            m.put(key, null);
        }

        Iterator<Map.Entry<Integer, String>> it = entrySet.iterator();
        Map.Entry<Integer, String> e  = it.next();
        it.remove();

        assertFalse(m.containsKey(e.getKey()));
    }

    @Test(expected = NullPointerException.class)
    public void testIfEntrySetRemoveMethodThrowsExceptionOnNullKey() {
        entrySet.remove(null);
    }

    @Test
    public void testIfEntrySetRemoveMethodReturnTrueOnSuccess() {
        m.put(key, "abc");

        Iterator<Map.Entry<Integer, String>> it = entrySet.iterator();
        Map.Entry<Integer, String> entry = it.next();

        assertTrue(entrySet.remove(entry));
    }

    @Test
    public void testIfEntrySetRemoveMethodReturnFalseIfMapDoesntContainSuchKey() {
        assertFalse(entrySet.remove(key));
    }

    @Test
    public void testIfEntrySetRemoveAllMethodTrulyRemoveElementsFromMap() {

        for (Integer i : values) {
            m.put(i, null);
        }

        entrySet.removeAll(m.entrySet());

        assertTrue(m.isEmpty());
    }

    @Test(expected = NullPointerException.class)
    public void testIfEntrySetRemoveAllMethodThrowsExceptionIfKeyIsNull() {
        entrySet.removeAll(null);
    }


    @Test
    public void testIfEntrySetRemoveAllMethodReturnTrueOnSuccess() {
        for (Integer i : values) {
            m.put(i, null);
        }

        assertTrue(entrySet.removeAll(m.entrySet()));
    }

     @Test
    public void testIfEntrySetRemoveAllMethodReturnFalseIfMapDoesntContainSuchKey() {
        assertFalse(entrySet.removeAll(Collections.singletonList(key)));
    }

    @Test
    public void testIfEntrySetRetainAllMethodTrulyRemoveElementsFromMap() {

        CustomHashMap<Integer, String> that = new CustomHashMap<>();

        int[] src = {1, 2, 3, 4, 5};
        int[] remove = {1, 2, 3};
        int[] retain = {4, 5};

        for (Integer i : src) {
            m.put(i, null);
        }

        for (Integer i : retain) {
            that.put(i, null);
        }


        entrySet.retainAll(that.entrySet());

        for (Integer i : remove) {
            assertFalse(m.containsKey(i));
        }

        for (Integer i : retain) {
            assertTrue(m.containsKey(i));
        }
    }

    @Test
    public void testIfEntrySetClearMethodTrulyClearMap() {
        for (Integer i : values) {
            m.put(i, null);
        }

        entrySet.clear();
        assertTrue(m.isEmpty());
    }

    @Test
    public void testIfHashMapClearedByEntrySetMethodDoesntContainElements() {
        for (Integer i : values) {
            m.put(i, null);
        }

        entrySet.clear();
        for (Integer i : values) {
            assertFalse(m.containsKey(i));
        }
    }

    @Test
    public void testIfEntrySetSizeReturnZeroOnEmptyMap() {
        assertThat(entrySet.size(), is(0));
    }

    @Test
    public void testIfEntrySetSizeEqualsToMapSize() {
        for (Integer i : values) {
            m.put(i, null);
        }

        assertThat(entrySet.size(), is(m.size()));
    }

    @Test
    public void testIfEqualMethodReturnTrueOnTheSameMap() {
        for (Integer i : values) {
            m.put(i, null);
        }

        assertTrue(m.equals(m));
    }

    @Test
    public void testIfEqualReturnFalseOnTwoDifferentMaps() {
        CustomHashMap<String, String> that = new CustomHashMap<>();
        that.put("qqq", "lll");

        for (Integer i : values) {
            m.put(i, null);
        }

        assertFalse(that.equals(m));
    }

    @Test
    public void testIfEqualReturnTrueOnTwoEqualMaps() {
        CustomHashMap<Integer, String> that = new CustomHashMap<>();

        for (Integer i : values) {
            m.put(i, null);
            that.put(i, null);
        }

        assertTrue(that.equals(m));
    }

    @Test
    public void testIfEqualReturnFalseOnNullArgument() {
        assertFalse(m.equals(null));
    }

    @Test (expected = NullPointerException.class)
    public void testIfPutAllThrowsExceptionOnNullArgument() {
        m.putAll(null);
    }

    @Test
    public void testIfPutAllTrulyPutElementsToTheMap() {
        CustomHashMap<Integer, String> that = new CustomHashMap<>();
        m.put(10, "abc");
        that.put(10, "test1");
        that.put(1598, "test2");
        m.putAll(that);

        assertTrue(m.containsValue("test1"));
        assertTrue(m.containsKey(1598));
        assertTrue(m.size() == 2);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testIfValueSetAddMethodThrowsExceptionOnAdd() {
        valueSet.add("a");
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testIfValueSetAddAllMethodThrowsExceptionOnAdd() {
        valueSet.addAll(Collections.singletonList("a"));
    }

    @Test
    public void testIfValueSetSizeReturnZeroOnEmptyMap() {
        assertThat(valueSet.size(), is(0));
    }

    @Test
    public void testIfValueSetSizeEqualsToMapSize() {
        for (Integer i : values) {
            m.put(i, null);
        }

        assertThat(valueSet.size(), is(m.size()));
    }

    @Test
    public void testIfValueSetContainsWorksAsExpected() {
        m.put(100, "abc");
        m.put(200, null);

        assertTrue(valueSet.contains("abc"));
        assertTrue(valueSet.contains(null));
    }

    @Test
    public void testIfValueSetIteratorRemoveMethodTrulyRemoveElementFromMap() {

        m.put(100, "abc");
        m.put(200, "cba");


        Iterator<String> it = valueSet.iterator();
        it.next();
        it.remove();

        assertThat(m.size(), is(1));
    }

    @Test (expected = IllegalArgumentException.class)
    public void shouldThrowExceptionIfInitialCapacityLessOne() {
        System.out.println(1 << 30);
        new CustomHashMap<>(0);
    }
}