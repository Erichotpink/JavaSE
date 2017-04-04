package com.epam.javase.list;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.*;

import static junit.framework.TestCase.assertFalse;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertTrue;

/**
 * Created by aivanov on 4/4/2017.
 */

@RunWith(Parameterized.class)
public class CustomListsTest {

    private List<String> list;
    private final String[] values = {"abc", "qwe", "asd", "zxc"};

    public CustomListsTest(List<String> list) {
        this.list = list;
    }

    @Parameterized.Parameters
    public static Collection<Object> data() {
        return Arrays.asList(new Object[]{
                new CustomArrayList(),
                new CustomLinkedList()
        });
    }

    @Before
    public void init() {
        list.clear();
    }

    @Test
    public void testThatNewListIsEmpty() {
        assertTrue(list.isEmpty());
    }

    @Test
    public void testThatListNotEmptyAfterAddingElement() {
        list.add("aaaa");
        assertThat(list.isEmpty(), is(false));
    }

    @Test
    public void testThatListContainsElementThatWeAddedBefore() {
        String value = "bbb";

        list.add(value);

        assertTrue(list.contains(value));
    }

    @Test
    public void testThatListNotContainsElementThatWasntAddedToList() throws Exception {
        list.add("fff");
        assertFalse(list.contains("ccc"));
    }

    @Test
    public void testThatListContainsNullIfItWasAdded() {

        list.add(null);

        assertTrue(list.contains(null));
    }

    @Test
    public void testThatListNotContainsNullIfItWasNotAdded() {
        list.add("fff");
        assertFalse(list.contains(null));
    }

    @Test
    public void testThatListsSizeIsDynamic() throws Exception {
        int size = 50;

        for (int i = 0; i < size; i++) {
            list.add(String.valueOf(i));
        }

        assertThat(list.size(), is(size));
    }

    @Test
    public void testThatWeCanGetElementByIndex() {

        fillList();

        assertThat(list.get(1), is(equalTo("aa1a")));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testThatWeCantGetElementByIndexMoreThenSize() throws Exception {

        fillList();

        list.get(list.size());
    }

    @Test
    public void testThatWeCanRemoveExistedElementFromList() throws Exception {
        fillList();

        list.remove("ssss");

        assertFalse("contains", list.contains("ssss"));
    }

    @Test
    public void testThatWeCanDeleteElementByIndex() throws Exception {
        fillList();

        String removed = list.remove(2);

        assertFalse(list.contains("aa2a"));
        assertThat(removed, is(equalTo("aa2a")));
    }

    @Test
    public void testThatWeCanDeleteLastElement() throws Exception {
        fillList();

        int prevSize = list.size();

        list.remove(list.size() - 1);

        assertFalse(list.contains("aa4a"));
        assertThat(list.size(), is(equalTo(prevSize - 1)));
    }

    @Test
    public void testThatWeCantDeleteNonExistentElement() throws Exception {
        fillList();

        assertFalse(list.remove("sadasdasd"));
    }

    private void fillList() {
        list.add("aa0a");
        list.add("aa1a");
        list.add("aa2a");
        list.add("ssss");
        list.add("aa3a");
        list.add("aa4a");
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testIfRemoveByIndexThrowsExceptionOnIndexLargerThanSize() {
        list.add("a");
        list.add("b");

        list.remove(5);
    }

    @Test
    public void testIfToArrayReturnEmptyArrayOnEmptyList() {
        assertThat(list.toArray().length, is(0));
    }

    @Test
    public void testIfArrayCreatedByToArrayContainsAllListValues() {
        for (String s : values) {
            list.add(s);
        }

        Object[] temp = list.toArray();

        for (int i = 0; i < values.length; i++) {
            assertThat(temp[i], equalTo(values[i]));
        }
    }

    @Test
    public void testIfAddReturnTrueOnSuccess() {
        assertTrue(list.add(null));
    }

    @Test
    public void testIfRemoveReturnTrueOnSuccess() {
        list.add("aaa");
        assertTrue(list.remove("aaa"));
    }

    @Test
    public void testIfRemoveReturnFalseOnFail() {
        list.add("bbb");
        assertFalse(list.remove("aaa"));
    }

    @Test(expected = NullPointerException.class)
    public void testIfContainsAllThrowsExceptionOnNullArgument() {
        list.containsAll(null);
    }

    @Test
    public void testIfContainsAllReturnTrueOnSuccess() {
        for (String s : values) {
            list.add(s);
        }

        assertTrue(list.containsAll(Arrays.asList(values)));
    }

    @Test
    public void testIfContainsAllReturnFalseOnFail() {
        list.add(values[0]);
        assertFalse(list.containsAll(Arrays.asList(values)));
    }

}