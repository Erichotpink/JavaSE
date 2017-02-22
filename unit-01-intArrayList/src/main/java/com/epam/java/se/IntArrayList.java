package com.epam.java.se;

import java.util.Arrays;
import java.util.NoSuchElementException;

/**
 * Created by aivanov on 2/21/2017.
 */
public class IntArrayList {

    private int[] data;
    private int size;

    public IntArrayList(int[] data) {
        this.data = Arrays.copyOf(data, data.length);
        size = data.length;
    }

    public IntArrayList() {
        data = new int[10];
        size = 0;
    }

    public void add(int value) {
        ensureCapacity(size + 1);
        data[size] = value;
        size += 1;
    }

    public int get(int i) {
        if (i < 0 || i >= size) {
            throw new IndexOutOfBoundsException();
        }

        return data[i];
    }

    public int getSize() {
        return size;
    }

    public int maxValueInefficient() {
        if(size == 0) {
            throw new NoSuchElementException();
        }

        return maxValueRec(data, 0, size);
    }

    private int maxValueRec(int[] data, int startInclusive, int endExclusive) {
        final int length = endExclusive - startInclusive;

        if (length == 1) {
            return data[startInclusive];
        } else if (length == 0) {
            return Integer.MIN_VALUE;
        }

        final int mid = startInclusive + length/2;
        return Math.max(
                maxValueRec(data, startInclusive, mid),
                maxValueRec(data, mid, endExclusive)
        );
    }

    public void sort() {
        mergeSort(data, 0, getSize(), new int[getSize()]);
    }

    /**
     * Expects collection to be sorted.
     *
     * @param value value to find in collection
     * @return index of the value or -indexToInsert - 1
     */
    public int binarySearch(int value) {
        return binarySearchRecursive(0, getSize(), value);
    }

    private int binarySearchRecursive(int startInclusive, int endExclusive, int value) {
        final int length = endExclusive - startInclusive;
        final int mid = startInclusive + length / 2;

        if (endExclusive <= startInclusive) return (-startInclusive - 1);
        else if (value == data[mid]) return mid;
        else if (value > data[mid]) return binarySearchRecursive(mid + 1, endExclusive, value);
        else if (value < data[mid]) return binarySearchRecursive(startInclusive, mid, value);

        return Integer.MIN_VALUE;
    }

    private void mergeSort(int[] data, int startInclusive, int endExclusive, int[] aux) {

        final int length = endExclusive - startInclusive;

        if (length <= 1) {
            return;
        }

        final int mid = startInclusive + length / 2;

        mergeSort(data, startInclusive, mid, aux);
        mergeSort(data, mid, endExclusive, aux);

        merger(data, startInclusive, mid, endExclusive, aux);
    }

    private void merger(int[] data, int startInclusive, int mid, int endExclusive, int[] aux) {

        System.arraycopy(data, startInclusive, aux, startInclusive, endExclusive-startInclusive);

        int i = startInclusive;
        int j = mid;

        for (int k = startInclusive; k < endExclusive; k++) {
            if (i >= mid) data[k] = aux[j++];
            else if (j >= endExclusive) data[k] = aux[i++];
            else if (aux[i] < aux[j]) data[k] = aux[i++];
            else data[k] = aux[j++];
        }
    }


    private void ensureCapacity(int requiredCapacity) {
        if (requiredCapacity <= getCapacity()) {
            return;
        }
        final int newCapacity = Math.max(requiredCapacity, (getCapacity() * 3) / 2 + 1);
        data = Arrays.copyOf(data, newCapacity);
    }

    private int getCapacity() {
        return data.length;
    }
}
