package com.epam.javase.list;

/**
 * Created by aivanov on 4/4/2017.
 */

import java.util.*;

public class CustomArrayList<T> implements List<T> {

    public static final int CAPACITY = 10;

    private Object[] data = new Object[CAPACITY];
    private int size = 0;

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(Object o) {
        for (int i = 0; i < size; i++) {
            if (data[i] == null) {
                if (o == null) {
                    return true;
                }
            } else if (data[i].equals(o)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Iterator<T> iterator() {
        return new CommonIterator();
    }

    @Override
    public Object[] toArray() {
        return Arrays.copyOf(data, size);
    }

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

    @Override
    public boolean add(T t) {
        if (size == data.length) {
            int newLength = (data.length * 3) / 2 + 1;
            data = Arrays.copyOf(data, newLength);
        }
        data[size++] = t;
        return true;
    }

    @Override
    public boolean remove(Object o) {
        for (int i = 0; i < size; i++) {
            if (o.equals(data[i])) {
                remove(i);
                return true;
            }
        }
        return false;
    }

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

    @Override
    public boolean addAll(Collection<? extends T> c) {
        boolean isModified = false;

        for (T o : c) {
            add(o);
            isModified = true;
        }

        return isModified;
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        return false;
    }

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

    @Override
    public void clear() {
        data = new Object[CAPACITY];
        size = 0;
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        return (T) data[index];
    }

    @Override
    public T set(int index, T element) {
        checkBounds(index);

        T prevValue = (T) data[index];

        data[index] = element;

        return prevValue;
    }

    private void checkBounds(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("The specified value is out of the range 0 - " + data.length + "(exclusive)");
        }
    }

    @Override
    public void add(int index, T element) {

        checkBounds(index);

        data = Arrays.copyOf(data, data.length + 1);
        System.arraycopy(data, index, data, index + 1, data.length - index - 1 );

        data[index] = element;

        size++;
    }

    @Override
    public T remove(int index) {

        checkBounds(index);

        int length = data.length - index;
        T value = (T) data[index];
        System.arraycopy(data, index + 1, data, index, length-1);
        size--;
        return value;
    }

    @Override
    public int indexOf(Object o) {
        for (int i = 0; i < size; i++) {
            if ((data[i] == null && o == null) || (data[i] != null && data[i].equals(o))) {
                return i;
            }
        }

        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        int lastIndex = -1;

        for (int i = 0; i < size; i++) {
            if ((data[i] == null && o == null) || (data[i] != null && data[i].equals(o))) {
                lastIndex = i;
            }
        }

        return lastIndex;
    }

    @Override
    public ListIterator<T> listIterator() {
        return new ListIter(0);
    }

    @Override
    public ListIterator<T> listIterator(int index) {
        return new ListIter(index);
    }

    /**
     * This is a light implementation and it doesn't follow the list {@link List} interface contract.
     * This method return a new list containing elements according to the specified range.
     *
     * @param fromIndex low endpoint (inclusive) of the subList
     * @param toIndex high endpoint (exclusive) of the subList
     * @return a view of the specified range within this list
     * @throws IndexOutOfBoundsException for an illegal endpoint index value
     *         (<tt>fromIndex &lt; 0 || toIndex &gt; size ||
     *         fromIndex &gt; toIndex</tt>)
     */
    public List<T> subList(int fromIndex, int toIndex) {
        checkBounds(fromIndex);
        checkBounds(toIndex);

        List<T> list = new LinkedList<T>();
        ListIterator<T> it = listIterator(fromIndex);
        while(it.nextIndex() < toIndex) {
            list.add(it.next());
        }

        return list;
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

            CustomArrayList.this.remove(current);
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
            T value = (T) data[cursorPos];
            ++cursorPos;

            return value;
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

            T value = (T) data[--cursorPos];
            last = cursorPos;

            return value;
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

            CustomArrayList.this.remove(last);
            cursorPos = last;
            last = -1;
        }

        @Override
        public void set(T t) {
            if (last < 0) {
                throw new IllegalStateException();
            }

            CustomArrayList.this.set(last, t);
        }

        @Override
        public void add(T t) {
            if (isEmpty()) {
                CustomArrayList.this.add(t);
            } else {
                CustomArrayList.this.add(cursorPos, t);
            }

            last = -1;
            cursorPos++;
        }
    }
}