package com.epam.javase.hashmap;

import java.util.*;

/**
 * Created by aivanov on 3/24/2017.
 */
public class CustomHashMap<K, V> implements Map<K, V> {

    private static final int DEFAULT_CAPACITY = 16;

    private CustomEntry<K, V>[] buckets;

    private long size = 0;

    public CustomHashMap() {
        this(DEFAULT_CAPACITY);
    }

    public CustomHashMap(int capacity) {
        buckets = new CustomEntry[capacity];
    }

    @Override
    public int size() {
        return size > Integer.MAX_VALUE ? Integer.MAX_VALUE : (int) size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
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
            return entry.getValue();
        }

        return null;
    }

    @Override
    public V put(K key, V value) {
        Objects.requireNonNull(key);

        V oldValue = null;
        int index = getBucketIndex(key);

        if (buckets[index] == null) {
            buckets[index] = new CustomEntry<>(key, value);
            size++;
            return oldValue;
        }

        CustomEntry<K, V> current = findBucketEntryWithTheSameKey(key, index);

        if (Objects.isNull(current)) {
            current = new CustomEntry<>(key, value);
            current.setNext(buckets[index]);
            buckets[index] = current;
            size++;
        } else {
            oldValue = current.getValue();
            current.setValue(value);
        }

        return oldValue;
    }

    @Override
    public V remove(Object o) {
        Objects.requireNonNull(o);

        CustomEntry<K, V> entry = removeEntry(o);

        if (Objects.nonNull(entry)) {
            size--;
            return entry.getValue();
        };

        return null;
    }

    private CustomEntry<K, V> removeEntry(Object o) {

        K key = (K) o;

        int index = getBucketIndex(key);

        if (Objects.isNull(buckets[index])) {
            return null;
        }

        CustomEntry<K, V> current = buckets[index];

        if (current.getKey() == key) {
            buckets[index] = current.next();
            return current;
        } else {
            while (current.hasNext()) {
                if (current.next().getKey().equals(key)) {
                    CustomEntry<K, V> next = current.next();
                    current.setNext(next.next());
                    return next;
                }
             }
        }

        return null;
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> m) {
        // TO DO
        throw new UnsupportedOperationException();

    }

    @Override
    public void clear() {
        buckets = new CustomEntry[buckets.length];
        size = 0;
    }

    @Override
    public Set<K> keySet() {
        return new CustomKeySet<>();
    }

    @Override
    public Collection<V> values() {
        return null;
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        return new CustomEntrySet();
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

    private class CustomEntry<K, V> implements Map.Entry<K, V>, Iterator<CustomEntry<K, V>> {
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
            return next;
        }

        @Override
        public K getKey() {
            return key;
        }

        @Override
        public V getValue() {
            return value;
        }

        @Override
        public V setValue(V value) {
            return this.value = value;
        }

        public void setNext(CustomEntry<K, V> next) {
            this.next = next;
        }
    }

    final class CustomKeySet<K> extends AbstractSet<K> {

        @Override
        public boolean add(K o) {
            throw new UnsupportedOperationException();
        }

        @Override
        public boolean addAll(Collection<? extends K> c) {
            throw new UnsupportedOperationException();
        }

        @Override
        public void clear() {
            CustomHashMap.this.clear();
        }

        @Override
        public Iterator<K> iterator() {return new KeyIterator();}

        @Override
        public int size() {
            return CustomHashMap.this.size();
        }

        @Override
        public boolean remove(Object key) {
            return CustomHashMap.this.removeEntry(key) != null;
        }
    }

    final class KeyIterator implements Iterator {

        private CustomEntry<K, V> current = null;
        private CustomEntry<K, V> next = null;
        private CustomEntry<K, V>[] data = buckets;

        private int index = 0;

        public KeyIterator() {
            for (;index < data.length && data[index] == null; index++);

            if (index < data.length) {
                next = data[index];
            }
        }

        @Override
        public boolean hasNext() {
            return next != null;
        }

        private CustomEntry<K, V> getEntry() {

            if (index >= data.length) {
                throw new NoSuchElementException();
            }

            current = next;

            if (next.next() != null) {
                next = next.next();
            } else {
                for (++index; index < data.length && data[index] == null; index++);

                if (index < data.length) {
                    next = data[index];
                } else {
                    next = null;
                }
            }

            return current;
        }

        @Override
        public K next() {
            return getEntry().getKey();
        }

        @Override
        public void remove() {
            if (Objects.isNull(current)) {
                throw new IllegalStateException();
            }
            CustomHashMap.this.remove(current.getKey());
            current = null;
        }
    }

    final class CustomEntrySet extends AbstractSet<Map.Entry<K, V>> {

        @Override
        public Iterator<Map.Entry<K, V>> iterator() {
            return null;
        }

        @Override
        public int size() {
            return CustomHashMap.this.size();
        }

        @Override
        public boolean add(Map.Entry<K, V> o) {
            throw new UnsupportedOperationException();
        }

        @Override
        public boolean addAll(Collection<? extends Map.Entry<K, V>> c) {
            throw new UnsupportedOperationException();
        }

        @Override
        public boolean remove(Object key) {
            return CustomHashMap.this.removeEntry(key) != null;
        }

        @Override
        public void clear() {
            CustomHashMap.this.clear();
        }
    }



    public static void main(String[] args) {
        CustomHashMap<Integer, String> m = new CustomHashMap<>();
        for (int i = 0; i < 17; i++) {
            m.put(i, null);
        }

        Set<Integer> set = m.keySet();
        Iterator<Integer> it = set.iterator();

        set.stream().forEach(System.out::println);
    }
}
