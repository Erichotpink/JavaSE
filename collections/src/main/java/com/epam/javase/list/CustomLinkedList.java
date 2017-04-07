package com.epam.javase.list;

import java.util.*;
import java.util.List;

/**
 * Created by aivanov on 4/4/2017.
 */
public class CustomLinkedList<T> implements List<T> {

    private Node<T> head = new Node<>(null);
    private int size = 0;

    /**
     * {@inheritDoc}
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isEmpty() {
        return !head.hasNext();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean contains(Object o) {
        Node<T> node = head;
        while (node.hasNext()) {
            node = node.next;
            if (node.value == null) {
                if (o == null) {
                    return true;
                }
            } else if (node.value.equals(o)) {
                return true;
            }
        }
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Iterator<T> iterator() {
        return new CommonIterator();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Object[] toArray() {
        Object[] temp = new Object[size];
        Node<T> root = head;
        int i = 0;
        while (root.hasNext()) {
            temp[i] = root.next.value;
            root = root.next;
            i++;
        }
        return temp;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <T> T[] toArray(T[] a) {
        Objects.requireNonNull(a);

        if (a.length < size) {
            a = Arrays.copyOf(a, size);
        }

        Iterator<T> it = (Iterator<T>) iterator();
        int i = 0;

        while (it.hasNext()) {
            a[i] = it.next();
            i++;
        }

        if (a.length > size) {
            a[size] = null;
        }

        return a;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean add(T t) {
        Node<T> iterator = head;
        while (iterator.hasNext()) {
            iterator = iterator.next;
        }
        iterator.next = new Node<>(t);
        size++;
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean remove(Object o) {
        Node<T> current = head.next;
        Node<T> prev = head;
        while (current != null) {
            if (o.equals(current.value)) {
                prev.next = current.next;
                size--;
                return true;
            }
            prev = current;
            current = current.next;
        }
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean containsAll(Collection<?> c) {
        Objects.requireNonNull(c);

        for (Object element : c) {
            if (!contains(element)) {
                return false;
            }
        }

        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean addAll(Collection<? extends T> c) {
        boolean isModified = false;

        for (T o : c) {
            add(o);
            isModified = true;
        }
        return isModified;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean removeAll(Collection<?> c) {
        Objects.requireNonNull(c);

        boolean isModified = false;

        Iterator it = iterator();
        while (it.hasNext()) {
            if (c.contains(it.next())) {
                it.remove();
                isModified = true;
            }
        }

        return isModified;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean retainAll(Collection<?> c) {
        Objects.requireNonNull(c);

        boolean isModified = false;

        Iterator it = iterator();
        while(it.hasNext()) {
            if (!c.contains(it.next())) {
                it.remove();
                isModified = true;
            }
        }

        return isModified;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void clear() {
        head = new Node<>(null);
        size = 0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public T get(int index) {
        checkBounds(index);

        return getNodeByIndex(index).value;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public T set(int index, T element) {
        checkBounds(index);

        Node<T> node = getNodeByIndex(index);
        T prevValue = node.value;

        node.value = element;

        return prevValue;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void add(int index, T element) {
        checkBounds(index);

        Node<T> prev = getNodeByIndex(index - 1);
        Node<T> next = new Node<>(element);

        next.next = prev.next;
        prev.next = next;
        size++;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public T remove(int index) {

        checkBounds(index);

        Node<T> prev = getNodeByIndex(index - 1);

        size--;
        T value = prev.next.value;
        prev.next = prev.next.next;
        return value;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int indexOf(Object o) {
        for (int i = 0; i < size; i++) {
            if ((get(i) == null && o == null) || (get(i) != null && get(i).equals(o))) {
                return i;
            }
        }

        return -1;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int lastIndexOf(Object o) {
        int lastIndex = - 1;

        for (int i = 0; i < size; i++) {
            if ((get(i) == null && o == null) || (get(i) != null && get(i).equals(o))) {
                lastIndex = i;
            }
        }

        return lastIndex;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ListIterator<T> listIterator() {
        return new ListIter(0);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ListIterator<T> listIterator(int index) {
        checkBounds(index);

        return new ListIter(index);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<T> subList(int fromIndex, int toIndex) {
        return null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || !(o instanceof List)) return false;

        List that = (List) o;

        if (that.size() != size()) return false;

        for (int i = 0; i < size; i++) {
            if ((get(i) == null && that.get(i) != null) || !get(i).equals(that.get(i))) {
                return false;
            }
        }

        return true;
    }

    @Override
    public int hashCode() {
        int hashCode = 1;
        for (T e : this)
            hashCode = 31*hashCode + (e==null ? 0 : e.hashCode());
        return hashCode;
    }

    // ---------- Auxiliary methods ----------

    private class Node<T> {

        private Node<T> next;
        private T value;

        public Node(T value) {
            this.value = value;
        }

        public boolean hasNext() {
            return next != null;
        }

    }

    final class CommonIterator implements Iterator<T> {

        int current = -1;
        int next = 0;

        @Override
        public boolean hasNext() {
            return next < size();
        }

        @Override
        public T next() {
            if (next >= size()) {
                throw new NoSuchElementException();
            }

            current = next;
            next++;

            return get(current);
        }

        @Override
        public void remove() {
            if (current < 0) {
                throw new IllegalStateException();
            }

            CustomLinkedList.this.remove(current);
            current = -1;
            next--;
        }
    }

    final class ListIter implements ListIterator<T> {

        private int cursorPos;
        private int last;

        public ListIter(int startIndex) {
            cursorPos = startIndex;
            last = -1;
        }

        @Override
        public boolean hasNext() {
            return cursorPos < size;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }

            last = cursorPos;
            Node<T> node = getNodeByIndex(cursorPos);
            ++cursorPos;

            return node.value;
        }

        @Override
        public boolean hasPrevious() {
            return cursorPos > 0;
        }

        @Override
        public T previous() {
            if (!hasPrevious()) {
                throw new NoSuchElementException();
            }

            Node<T> node = getNodeByIndex(--cursorPos);
            last = cursorPos;

            return node.value;
        }

        @Override
        public int nextIndex() {
            return cursorPos;
        }

        @Override
        public int previousIndex() {
            return cursorPos - 1;
        }

        @Override
        public void remove() {
            if (last < 0) {
                throw new IllegalStateException();
            }

            CustomLinkedList.this.remove(last);
            cursorPos = last;
            last = -1;
        }

        @Override
        public void set(T t) {
            if (last < 0) {
                throw new IllegalStateException();
            }

            CustomLinkedList.this.set(last, t);
         }

        @Override
        public void add(T t) {
            if (isEmpty()) {
                CustomLinkedList.this.add(t);
            } else {
                CustomLinkedList.this.add(cursorPos, t);
            }

            last = -1;
            cursorPos++;
        }
    }

    private Node<T> getNodeByIndex(int index) {

        Node<T> current = head;

        for (int i = 0; i <= index; i++) {
            current = current.next;
        }
        return current;
    }

    private void checkBounds(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
    }
}
