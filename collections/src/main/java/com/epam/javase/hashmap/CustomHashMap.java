package com.epam.javase.hashmap;

import java.util.*;

/**
 * Created by aivanov on 3/24/2017.
 */
public class CustomHashMap<K, V> implements Map<K, V> {

    private static final int DEFAULT_CAPACITY = 16;
    private static final int MAX_CAPACITY = 1 << 30;
    private static final int MIN_CAPACITY = 1 ;
    private static final double DEFAULT_LOADFACTOR = 0.75f;
    private static final double MIN_LOADFACTOR = 0.5f;
    private static final double MAX_LOADFACTOR = 0.9f;

    private final double loadFactor;
    private int threshold;
    private long size = 0;
    private CustomEntry<K, V>[] buckets;

    public CustomHashMap() {
        this(DEFAULT_CAPACITY);
    }

    public CustomHashMap(int capacity) {
        this(capacity, DEFAULT_LOADFACTOR);
    }

    public CustomHashMap(int capacity, double loadFactor) {
        if (capacity < MIN_CAPACITY || capacity > MAX_CAPACITY) {
            throw new IllegalArgumentException("The specified capacity is out of range " + MIN_CAPACITY + " " + MAX_CAPACITY);
        }

        if (Double.compare(loadFactor, MIN_LOADFACTOR) < 0 || Double.compare(loadFactor, MAX_LOADFACTOR) > 0) {
            throw new IllegalArgumentException("The specified threshold is out of range " + MIN_LOADFACTOR + " " + MAX_LOADFACTOR);
        }

        this.loadFactor = loadFactor;
        buckets = new CustomEntry[capacity];
        threshold = calculateThreshold();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int size() {
        return size > Integer.MAX_VALUE ? Integer.MAX_VALUE : (int) size;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean containsKey(Object o) {
        Objects.requireNonNull(o);

        K key = (K) o;
        int index = getBucketIndex(key, buckets.length);
        return findBucketEntryWithTheSameKey(key, index) != null;
    }

    /**
     * {@inheritDoc}
     */
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

    /**
     * {@inheritDoc}
     */
    @Override
    public V get(Object o) {
        Objects.requireNonNull(o);

        K key = (K) o;

        int index = getBucketIndex(key, buckets.length);
        CustomEntry<K, V> entry = findBucketEntryWithTheSameKey(key, index);

        if (entry != null) {
            return entry.getValue();
        }

        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public V put(K key, V value) {
        Objects.requireNonNull(key);

        resizeMap();

        V oldValue = null;
        int index = getBucketIndex(key, buckets.length);

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

    /**
     * {@inheritDoc}
     */
    @Override
    public V remove(Object o) {
        Objects.requireNonNull(o);

        CustomEntry<K, V> entry = removeEntryByKey(o);

        if (Objects.nonNull(entry)) {
            size--;
            return entry.getValue();
        };

        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void putAll(Map<? extends K, ? extends V> m) {

        Objects.requireNonNull(m);

        for (Map.Entry<? extends K, ? extends V> entry : m.entrySet()) {
            this.put(entry.getKey(), entry.getValue());
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void clear() {
        buckets = new CustomEntry[buckets.length];
        size = 0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Set<K> keySet() {
        return new CustomKeySet();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Collection<V> values() {
        return new CustomValueSet();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Set<Map.Entry<K, V>> entrySet() {
        return new CustomEntrySet();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CustomHashMap<?, ?> that = (CustomHashMap<?, ?>) o;

        if (size != that.size) return false;
        return this.entrySet().equals(that.entrySet());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        int result = Arrays.hashCode(buckets);
        result = 31 * result + (int) (size ^ (size >>> 32));
        return result;
    }

    //
    // ---------- Auxiliary and non-public methods ----------
    //

    private int calculateThreshold() {
        return (int) (buckets.length * loadFactor);
    }

    private void resizeMap() {
        if (buckets.length >= MAX_CAPACITY || size <= threshold) {
            return;
        }

        int newCapacity = (buckets.length * 2 <= MAX_CAPACITY) ? buckets.length * 2 : MAX_CAPACITY;
        CustomEntry<K, V>[] newBuckets = new CustomEntry[newCapacity];
        for (Map.Entry<K, V> entry : entrySet()) {
            int index = getBucketIndex(entry.getKey(), newBuckets.length);

            if (newBuckets[index] == null) {
                newBuckets[index] = (CustomEntry<K, V>) entry;
                ((CustomEntry<K, V>) entry).setNext(null);
            } else {
                ((CustomEntry<K, V>) entry).setNext(newBuckets[index]);
                newBuckets[index] = (CustomEntry<K, V>) entry;
            }
        }

        buckets = newBuckets;
        threshold = calculateThreshold();
    }

    private int getBucketIndex(K key, int capacity) {
        int hashcode = key.hashCode();
        hashcode = hashcode & 0x7fffffff;
        return hashcode % capacity;
    }

    private CustomEntry<K, V> removeEntryByKey(Object o) {

        K key = (K) o;

        int index = getBucketIndex(key, buckets.length);

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

    private CustomEntry<K, V> findBucketEntryWithTheSameKey(K key, int index) {
        CustomEntry<K, V> current = buckets[index];
        for (;current != null && !current.key.equals(key); current = current.next());
        return current;
    }

    private CustomEntry<K, V> findBucketEntryWithTheSameValue(V value, int index) {
        CustomEntry<K, V> current = buckets[index];
        for (;current != null; current = current.next()) {
            V v = current.getValue();
            if ((v == null && value == null) ||
                    (v != null & v.equals(value))) {
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
            return next != null;
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

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            CustomEntry<?, ?> that = (CustomEntry<?, ?>) o;

            if (!key.equals(that.key)) return false;
            return value != null ? value.equals(that.value) : that.value == null;
        }

        @Override
        public int hashCode() {
            int result = key.hashCode();
            result = 31 * result + (value != null ? value.hashCode() : 0);
            return result;
        }
    }

    final class CustomKeySet extends AbstractSet<K> {

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
            Objects.requireNonNull(key);

            CustomEntry<K, V> entry = removeEntryByKey(key);
            if (entry != null) {
                size--;
                return true;
            }

            return false;
        }
    }

    final class CustomEntrySet extends AbstractSet<Map.Entry<K, V>> {

        @Override
        public Iterator<Map.Entry<K, V>> iterator() {
            return new EntryIterator();
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
        public boolean remove(Object o) {

            Objects.requireNonNull(o);

            if (o instanceof Map.Entry) {
                Map.Entry<K, V> entry = (Map.Entry<K, V>) o;
                K key = entry.getKey();
                V value = entry.getValue();
                if (CustomHashMap.this.containsKey(key) &&
                        CustomHashMap.this.get(key).equals(value)) {
                    return CustomHashMap.this.removeEntryByKey(key) != null;
                }
            }
            return false;
        }

        @Override
        public void clear() {
            CustomHashMap.this.clear();
        }
    }

    final class CustomValueSet extends AbstractCollection<V> {

        @Override
        public boolean add(V o) {
            throw new UnsupportedOperationException();
        }

        @Override
        public boolean addAll(Collection<? extends V> c) {
            throw new UnsupportedOperationException();
        }

        @Override
        public void clear() {
            CustomHashMap.this.clear();
        }

        @Override
        public Iterator<V> iterator() {return new ValueIterator();}

        @Override
        public int size() {
            return CustomHashMap.this.size();
        }

        @Override
        public final boolean contains(Object o) { return containsValue(o); }

    }

    abstract class CommonIterator {

        private CustomEntry<K, V> current = null;
        private CustomEntry<K, V> next = null;
        private CustomEntry<K, V>[] data = buckets;
        private int index = 0;

        public CommonIterator() {
            for (;index < data.length && data[index] == null; index++);

            if (index < data.length) {
                next = data[index];
            }
        }

        public final boolean hasNext() {
            return next != null;
        }

        final CustomEntry<K, V> getEntry() {

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

        public final void remove() {
            if (Objects.isNull(current)) {
                throw new IllegalStateException();
            }
            CustomHashMap.this.remove(current.getKey());
            current = null;
        }

    }

    final class KeyIterator extends CommonIterator implements Iterator<K> {
        @Override
        public K next() {
            return getEntry().getKey();
        }
    }

    final class EntryIterator extends CommonIterator implements Iterator<Map.Entry<K, V>> {
        @Override
        public Map.Entry<K, V> next() {
            return getEntry();
        }
    }

    final class ValueIterator extends CommonIterator implements Iterator<V> {
        @Override
        public V next() {
            return getEntry().getValue();
        }
    }
}
