package com.epam.javase.hashmap;

import sun.security.krb5.internal.ccache.CCacheInputStream;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by aivanov on 3/24/2017.
 */
public class CustomHashMap<K, V> implements Map<K, V> {

    private static final int DEFAULT_CAPACITY = 16;

    private CustomEntry<K, V>[] buckets;

    public CustomHashMap() {
        this(DEFAULT_CAPACITY);
    }

    public CustomHashMap(int capacity) {
        buckets = new CustomEntry[capacity];
    }


    @Override
    public int size() {

        long count = 0;

        for (int i = 0; i < buckets.length; i++) {
            if (buckets[i] != null) {
                count++;
                CustomEntry entry = buckets[i];
                while(entry.hasNext()) {
                    count++;
                    entry = entry.next;
                }
            }
        }

        return count > Integer.MAX_VALUE ? Integer.MAX_VALUE : (int) count;

    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean containsKey(Object o) {
        Objects.requireNonNull(o);

        K key = (K) o;
        int index = getBucketIndex(key);
        return findBucketEntryWithTheSameKey(key, index) != null;
    }

    @Override
    public boolean containsValue(Object o) {
        V value = (V) o;
        for (int i = 0; i < buckets.length; i++) {
            if (findBucketEntryWithTheSameValue(value, i) != null) {
                return true;
            }
        }

        return false;
    }

    @Override
    public V get(Object o) {
        Objects.requireNonNull(o);

        K key = (K) o;

        int index = getBucketIndex(key);
        CustomEntry<K, V> entry = findBucketEntryWithTheSameKey(key, index);

        if (entry != null) {
            return entry.value;
        }

        return null;
    }

    @Override
    public V put(K key, V value) {
        Objects.requireNonNull(key);

        int index = getBucketIndex(key);

        if (buckets[index] == null) {
            buckets[index] = new CustomEntry<>(key, value);
            return null;
        }

        CustomEntry<K, V> current = findBucketEntryWithTheSameKey(key, index);
        if (Objects.isNull(current)) {
            current = new CustomEntry<>(key, value);
            current.next = buckets[index];
            buckets[index] = current;
            return null;
        } else {
            V oldValue = current.value;
            current.value = value;
            return oldValue;
        }
    }

    @Override
    public V remove(Object key) {
        return null;
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> m) {

    }

    @Override
    public void clear() {
        buckets = new CustomEntry[buckets.length];
    }

    @Override
    public Set<K> keySet() {
        return null;
    }

    @Override
    public Collection<V> values() {
        return null;
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        return null;
    }

    private int getBucketIndex(K key) {
        int hashcode = key.hashCode();
        hashcode = hashcode & 0x7fffffff;
        return hashcode % buckets.length;
    }

    private CustomEntry<K, V> findBucketEntryWithTheSameKey(K key, int index) {
        CustomEntry<K, V> current = buckets[index];
        for (;current != null && !current.key.equals(key); current = current.next());
        return current;
    }

    private CustomEntry<K, V> findBucketEntryWithTheSameValue(V value, int index) {
        CustomEntry<K, V> current = buckets[index];
        for (;current != null; current = current.next()) {
            if (current.value == value || value.equals(current.value)) {
                return current;
            }
        }
        return null;
    }

    private class CustomEntry<K, V> implements Iterator<CustomEntry<K, V>> {
        private final K key;
        private V value;
        private CustomEntry<K, V> next = null;

        public CustomEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public boolean hasNext() {
            return false;
        }

        @Override
        public CustomEntry<K, V> next() {
            return null;
        }

        public void setNext(CustomEntry<K, V> next) {
            this.next = next;
        }
    }
}
