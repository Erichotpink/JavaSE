package com.epam.javase.hashmap;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsNull.notNullValue;

/**
 * Created by aivanov on 3/24/2017.
 */

@FixMethodOrder
public class CustomHashMapTest {

    private Map<Integer, String> m;

    @Before
    public void init() {
        m = new CustomHashMap<>();
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
        Integer key = new Integer(10);

        String oldValue = "abc";
        String newValue = "cba";
        m.put(key, oldValue);
        m.put(key, newValue);

        assertThat(m.get(key), is(newValue));
    }

    @Test
    public void testIfPutMethodReturnValueWhenPutTheSameKeyWithDifferentValue() {

    }

    @Test(expected = NullPointerException.class)
    public void testIfGetThrowsExceptionIfKeyIsNull() {
        m.get(null);
    }

//    @Test(expected = ClassCastException.class)
//    @Test
//    public void testIfGetThrowsExceptionIfWeSpecifyWrongKeyClass() {
//        m.get(new CustomHashMapTest());
//    }

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

    @Test(expected = ClassCastException.class)
    public void testThatContainsKeyMethodThrowsExceptionOnWrongKeyClass() {
    }

    @Test
    public void testContainsValueMethodWorksProperly() {
    }

    @Test
    public void testContainsValueMethodWorksProperlyOnNullInputValue() {
    }

    @Test(expected = ClassCastException.class)
    public void testValueContainsMethodThrowsExceptionOnWrongInputValueClass() {
    }

    @Test
    public void testThatMapCalculateItsSizeProperly() {

    }


}