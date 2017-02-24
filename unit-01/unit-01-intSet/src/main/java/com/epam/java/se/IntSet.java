package com.epam.java.se;

import java.util.Arrays;

/**
 * The class implement a lightweight version of bitSet data structure to storage positive integer values.
 * The implementation based on array of long values where every bit refer to a specific integer value.
 * Every element of the array contains information about 64 integer values.
 * In order to find out if the data structure contains specific value you need to divide the value on 64.
 * The result of division is the index of an array element and the rest is the bit index.
 *
 *
 * Created by Andrey Ivanov on 2/1/2017.
 */
public class IntSet {


    private static final int BIT_COUNT = 64;
    private static final int MAX_ARRAY_SIZE = (Integer.MAX_VALUE / BIT_COUNT) + 1;

    private static final int DEFAULT_LENGTH = 10;

    private long[] data;
    private long size = 0;  // Contains number of records


    /**
     * Creates a bit set with default length.
     */
    public IntSet() {
        this(DEFAULT_LENGTH);
    }

    /**
     * Creates a bit set with the specified length.
     */
    public IntSet(int length) {
        data = new long[length];
    }

    /**
     * Create a bit set containing the elements of the specified IntSet.
     * @param obj the IntSet whose element are to be placed into the IntSet
     * @throws java.lang.NullPointerException if the specified object in null
     */
    public IntSet(IntSet obj) {
        data = Arrays.copyOf(obj.data, obj.data.length);
    }

    /**
     * Set a bit for the specified value to true.
     * @param value contains integer value to be placed into the IntSet
     * @throws java.lang.IllegalArgumentException if the value is negative
     */
    public void add(int value) {

        if (value < 0) {
            throw new IllegalArgumentException("The specified value isn't positive.");
        }

        if (!contains(value)) {
            int index = getSetIndex(value);
            ensureCapacity(index + 1);
            setBit(value, true);
            size++;
        }
    }

    /**
     * Set a bit for the specified value to false.
     * @param value contains integer value to be removed from the IntSet
     * @throws java.lang.IllegalArgumentException if the value is negative
     */
    public void remove(int value) {

        if (value < 0) {
            throw new IllegalArgumentException("The specified value isn't positive.");
        }

        if (contains(value)) {
            setBit(value, false);
            size--;
        }
    }

    /**
     * Check if the IntSet contains the specified value.
     * @param value contains the value whose presence in the IntSet is to be checked
     * @return true if the specified value is in the IntSet
     * @throw java.lang.IllegalArgumentException if the value is negative
     */
    public boolean contains(int value) {

        if (value < 0) {
            throw new IllegalArgumentException("The specified value isn't positive.");
        }

        int index = getSetIndex(value);

        if (data.length < index + 1) {
            return false;
        }

        final long mask = 1L << (value % BIT_COUNT);

        return (data[index] & mask) != 0;
    }

    /**
     * Combine values of the IntSet with the values in the specified IntSet.
     *
     * @param other IntSet containing values to be added to the result IntSet
     * @return a new IntSet containing values from this and specified IntSet
     */
    public IntSet union(IntSet other) {

        IntSet result = new IntSet(this);

        if (this == other) {
            return result;
        }

        result.addAll(other);

        return result;
    }

    /**
     * Find values in common between two array
     *
     * @param other IntSet containing values to be checked against this IntSet
     * @return a new IntSet containing values common to two IntSet
     */
    public IntSet intersection(IntSet other) {

        if (this == other) {
            return new IntSet(this);
        }

        final int min = Math.min(data.length, other.data.length);

        IntSet result = new IntSet(min);

        for (int i = 0; i < min ; i++) {
            result.data[i] = this.data[i] & other.data[i];
        }

        return result;
    }

    /**
     * Find the unique values of the IntSet
     *
     * @param other IntSet containing values to be excluded from the IntSet
     * @return a new IntSet containing values unique to the source IntSet
     */
    public IntSet difference(IntSet other) {

        if (this == other) {
            return new IntSet();
        }

        final int min = Math.min(this.data.length, other.data.length);
        IntSet result = new IntSet(this);

        for (int i = 0; i < min ; i++) {
            result.data[i] &=  (~other.data[i]);
        }

        return result;
    }

    /**
     * Check if the specified IntSet contains values from the IntSet
     *
     * @param other IntSet containing values whose elements are common to two IntSet
     * @return true if the specified IntSet contains values of the IntSet
     */
    public boolean isSubsetOf(IntSet other) {

        if (this == other) {
            return true;
        }

        final int min = getLastNotNull(this);

        if (size > other.size || min > getLastNotNull(other)) {
            return false;
        }

        long mask = 0L;

        for (int i = 0; i <= min; i++) {
            mask = other.data[i] ^ data[i];
            if ((mask & data[i]) != 0) {
                return false;
            }
        }

        return true;
    }

    /**
     * Appends all values of the specified IntSet to the IntSet
     *
     * @param other IntSet containing values to be added to the IntSet
     */
    public void addAll(IntSet other) {

        if (this == other) {
            return;
        }

        final int length = other.data.length;
        ensureCapacity(length);

        for (int i = 0; i < length; i++) {
            data[i] |= other.data[i];
        }
    }

    /**
     * Trim the capacity of the IntSet.
     */
    public void trimToSize() {

        int newSize = getLastNotNull(this) + 1;
        if (newSize < data.length) {
            data = Arrays.copyOf(data, newSize);
        }
    }

    /**
     * Returns a string representation of the IntSet
     * @return a string representation of the IntSet
     */
    public String toString() {
        String str = "";
        for(int i = 0; i < data.length; i++) {
            long mask = 1L;
            for (int j = 0; j < BIT_COUNT; j++) {
                if ((data[i] & mask) != 0) {
                    str = str + ((BIT_COUNT * i) + j) + " ";
                }
                mask <<= 1;
            }
        }
        return str;
    }


    /**
     * Find the index of an element containing bit corresponding to this values
     *
     * @param value integer value whose index is to be found
     * @return index of the element
     */
    private int getSetIndex(int value) {
        return (value / BIT_COUNT);
    }

    /**
     * Increase the capacity of the IntSet, if necessary
     * @param size the desired capacity
     */
    private void ensureCapacity(int size) {

        if (size < 0 || size > MAX_ARRAY_SIZE) {
            throw new IllegalArgumentException("The specified size is out of the range 0 - "
                    + (MAX_ARRAY_SIZE) + "(Inclusive)");
        }

        if (data.length < size) {
            data = Arrays.copyOf(data, size);
        }
    }

    /**
     * Set the bit flag of the specified integer value
     *
     * @param value the integer value whose bit is to be changed
     * @param flag a value to be set on the bit
     */
    private void setBit(int value, boolean flag) {
        final int index = getSetIndex(value);
        final long mask = 1L << (value % BIT_COUNT);

        if (flag) {
            data[index] |= mask;
        } else {
            data[index] ^= mask;
        }
    }

    /**
     * Find the last index of non-null element in the IntSet
     *
     * @param obj IntSet to be checked
     * @return index of the last non-null element
     */
    private int getLastNotNull(IntSet obj) {
        for (int i = 0; i < obj.data.length; i++) {
            if (obj.data[i] != 0) {
                return i;
            }
        }
        return -1;
    }
}
