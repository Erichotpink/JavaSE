package com.epam.javase.treemap;

import static org.junit.Assert.*;


import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;

import java.util.Date;
import java.util.Map;
import java.util.stream.IntStream;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by aivanov on 3/24/2017.
 */

@FixMethodOrder
public class CustomTreeMapTest {

    private Map<Integer, String> m;
    private final Integer key = new Integer(1000);
    private final String value = "abc";

    @Before
    public void init() {
        m = new CustomTreeMap<>();
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
    public void testThatNonEmptyMapIsntEmpty() {
        m.put(key, value);
        assertFalse(m.isEmpty());
    }

    @Test
    public void testThatOnNewMapContainKeyMethodReturnFalseForAnyObject() {
        assertThat(m.containsKey(new Integer(1)), is(false));
    }

    @Test
    public void testThatWeCanPutKeyValuePairAndCanCheckIt() {
        m.put(key, value);
        assertThat(m.containsKey(key), is(true));
    }

    @Test(expected = NullPointerException.class)
    public void testThatWeCantPutNullKey() {
        m.put(null, "abc");
    }

    @Test
    public void testThatWeCanPutNullValue() {
        m.put(key, null);
        assertThat(m.containsKey(key), is(true));
    }

    @Test
    public void testThatMapCanPutPairWithKeyThatAlreadyPresented() {

        String oldValue = "aaaa";
        String newValue = "bbbb";

        m.put(1, oldValue);
        m.put(1, newValue);

        assertFalse(m.containsValue(oldValue));
        assertTrue(m.containsValue(newValue));
    }

    @Test
    public void testThatIfWePutNewValueOnExistingKeyPreviousValueWillBeReturned() {
        String oldValue = "aaaa";
        String newValue = "bbbb";

        m.put(key, oldValue);

        assertThat(m.put(key, newValue), equalTo(oldValue));
    }

    @Test
    public void testThatPutValueIncreaseSizeWhenKeyDoesntPresent() {
        m.put(key, value);
        assertThat(m.size(), is(1));
    }

    @Test
    public void testThatPutValueDontIncreaseSizeWhenKeyPresent() {
        m.put(key, value);
        m.put(key, "bbnn");

        assertThat(m.size(), is(1));
    }

    @Test(expected = NullPointerException.class)
    public void testThatContainsKeyMethodThrowsExceptionOnNullKey() {
        m.containsKey(null);
    }

    @Test
    public void testIfContainsKeyMethodReturnTrueOnSuccess() {
        m.put(10, "abc");

        assertTrue(m.containsKey(10));
    }

    @Test
    public void testIfContainsKeyMethodReturnFalseOnFailure() {
        m.put(10, "abc");

        assertFalse(m.containsKey(11));
    }

    @Test(expected = ClassCastException.class)
    public void testThatContainsKeyMethodThrowsExceptionOnWrongKeyClass() {
        m.put(1, ""); //TODO need to remove
        m.containsKey(new String(""));
    }

    @Test
    public void testContainsValueMethodWorksProperlyOnNullInputValue() {
        m.put(key, null);

        assertTrue(m.containsValue(null));
    }

    @Test
    public void testEmptyTreeDoesntContainNullValue() {
        assertFalse(m.containsValue(null));
    }

    @Test
    public void testThatWeCanPut10DifferentKeysInMap() {
        IntStream.range(1, 10).forEach(
                i -> m.put(i, String.valueOf(i))
        );

        IntStream.range(1, 10).forEach(
                i -> assertTrue(m.containsKey(i))
        );
    }

    @Ignore
    @Test(expected = ClassCastException.class)
    public void testValueContainsMethodThrowsExceptionOnWrongInputValueClass() {
    }

    @Test
    public void testContainsValueMethodWorksProperly() {
        m.put(key, value);
        assertTrue(m.containsValue(value));
    }

    @Test
    public void testContainsValueMethodReturnFalseOnFailure() {
        m.put(key, "abc");
        assertFalse(m.containsValue("cba"));
    }

    @Test
    public void testContainsMethodAfterBulkInsertion() {
        for (int i = 0; i < 1000; i++) {
            m.put(new Integer(i), null);
        }

        for (int i = 0; i < 1000; i++) {
            assertTrue(m.containsKey(new Integer(i)));
        }
    }

    @Test
    public void testThatMapCalculateItsSizeProperly() {
    }

    @Test
    public void testIfClearWorksAsExpected() {
        m.put(1, null);
        m.clear();

        assertTrue(m.isEmpty());
        assertFalse(m.containsKey(1));
    }

    @Test(expected = NullPointerException.class)
    public void testIfGetThrowsExceptionIfKeyIsNull() {
        m.get(null);
    }

    @Test(expected = ClassCastException.class)
    public void testIfGetThrowsExceptionIfWeSpecifyWrongKeyClass() {
        m.get(new Thread());
    }

    @Test
    public void testIfGetReturnNullIfTheKeyWasntFound() {

        assertNull(m.get(key));
    }


    @Test
    public void testIfGetReturnExpectedValueWhenMapContainsKey() {
        m.put(key, value);

        assertThat(m.get(key), is(value));
    }

    @Test
    public void testIfGetReturnNullWhenMapContainsKeyAndValueEqualsNull() {
        m.put(key, null);

        assertNull(m.get(key));
    }



}