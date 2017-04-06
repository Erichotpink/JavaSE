package com.epam.javase.list;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.*;

import static junit.framework.TestCase.assertFalse;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
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

    @Test
    public void testIfAddAllTrulyAddElementsToTheEnd() {
        String[] toAdd = {"s1", "s2", "s3"};
        for (String s : values) {
            list.add(s);
        }

        int size = list.size();

        list.addAll(Arrays.asList(toAdd));

        for (int i = 0; i < toAdd.length; i++) {
            assertThat(list.get(size + i), equalTo(toAdd[i]));
        }
    }


    @Test(expected = NullPointerException.class)
    public void testIfRemoveAllThrowsExceptionOnNullArgument() {
        list.removeAll(null);
    }

    @Test
    public void testIfRemoveTrulyRemoveOnlyOneInstanceOfElement() {
        list.add("aaa");
        list.add("aaa");
        list.add("aaa");
        list.add("aaa");

        list.remove("aaa");

        assertTrue(list.contains("aaa"));
        assertThat(list.size(), is(3));
    }

    @Test
    public void testRemoveBoundaryElements() {
        list.add("aaa");
        list.add("bbb");
        list.add("ccc");

        assertThat(list.remove(2), equalTo("ccc"));
        assertThat(list.remove(0), equalTo("aaa"));
        assertThat(list.size(), is(1));
    }

    @Test
    public void testIfRemoveAllRemoveOnlyExistedElements() {
        String[] init = {"a1", "a2", "a3"};
        String[] toRemove = {"a2", "a3", "a4"};

        list.addAll(Arrays.asList(init));
        list.removeAll(Arrays.asList(toRemove));

        assertTrue(list.contains("a1"));
        assertThat(list.size(), is(1));

    }

    @Test
    public void testIfRemoveAllReturnTrueOnSuccess() {
        list.add("aaa");
        assertTrue(list.removeAll(Collections.nCopies(10, "aaa")));
    }

    @Test
    public void testIfRemoveAllReturnFalseOnFail() {
        list.add("aaa");
        assertFalse(list.removeAll(Collections.singleton("bbb")));
    }

    @Test
    public void testIfRemoveAllRemoveAllInstancesOfEqualsElements() {
        list.add("aaa");
        list.add("aaa");
        list.add("aaa");
        list.add("aaa");

        list.removeAll(Collections.singleton("aaa"));
        assertTrue(list.isEmpty());
    }

    @Test
    public void testIfIteratorHasNextReturnFalseOnEmptyList() {
        assertFalse(list.iterator().hasNext());
    }

    @Test
    public void testIfIteratorHasNextReturnTrueOnNonEmptyList() {
        list.add("aaa");

        for (int i = 0; i < 10; i++) {
            assertTrue(list.iterator().hasNext());
        }
    }

    @Test
    public void testIfIteratorNextReturnNextOnNonEmptyList() {
        list.add("aaa");

        assertThat(list.iterator().next(), equalTo("aaa"));
    }

    @Test(expected = NoSuchElementException.class)
    public void testIfIteratorNextThrowsExceptionOnEmptyList() {

        Iterator it = list.iterator();
        it.next();
    }

    @Test(expected = NoSuchElementException.class)
    public void testIfIteratorNextThrowsExceptionOnMultipleInvocation() {
        list.add("aaa");

        Iterator it = list.iterator();
        it.next();
        it.next();
        it.next();
    }

    @Test
    public void testIfIteratorRemoveWorksAsExpected() {
        list.add("aaa");

        Iterator it = list.iterator();

        it.next();
        it.remove();
        assertTrue(list.isEmpty());
    }

    @Test(expected = IllegalStateException.class)
    public void testIfIteratorRemoveThrowsExceptionOnWrongInvocation() {
        list.add("aaa");

        Iterator it = list.iterator();
        it.remove();
    }

    @Test
    public void testListIteratorKeepsRightOrderAndRemoveAllElementsByIterator() {

        String[] temp = {"aaa", "bbb", "ccc"};

        list.addAll(Arrays.asList(temp));

        Iterator it = list.iterator();
        int i = 0;

        while(it.hasNext()) {
            assertThat(it.next(), equalTo(temp[i]));
            it.remove();
            i++;
        }

        assertTrue(list.isEmpty());
    }

    @Test
    public void testIfRetainAllRemoveAllNonSharedElements() {
        String[] init = {"aaa", "aaa", "bbb", "ccc", "ddd", "ddd"};
        String[] retain = {"bbb", "ddd"};

        list.addAll(Arrays.asList(init));
        list.retainAll(Arrays.asList(retain));

        assertThat(list.size(), is(retain.length + 1));
        assertTrue(list.containsAll(Arrays.asList(retain)));
    }

    @Test
    public void testIfEqualReturnTrueOnSuccess() {
        list.addAll(Arrays.asList(values));

        List that = Arrays.asList(values);

        assertTrue(list.equals(that));
    }

    @Test
    public void testIfEqualReturnFalseOnFail() {
        list.addAll(Arrays.asList(values));

        List that = Collections.singletonList("aaa");

        assertFalse(list.equals(that));
    }
}