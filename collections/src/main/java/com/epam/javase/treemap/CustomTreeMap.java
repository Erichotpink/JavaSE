package com.epam.javase.treemap;

import java.util.*;

/**
 * Created by aivanov on 3/24/2017.
 */

public class CustomTreeMap<K extends Comparable<K>, V> implements Map<K, V> {

    private Node<K, V> root;
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
        return size == 0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean containsKey(Object key) {
        Objects.requireNonNull(key);

        if (root == null) return false;

        if (!key.getClass().equals(root.key.getClass())) {
            throw new ClassCastException();
        }

        return find(root, (K) key) != null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean containsValue(Object o) {

        if (root == null) return false;

        V value = (V) o;

        return findNodeByValue(root, value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public V get(Object key) {
        Objects.requireNonNull(key);

        Node<K, V> node = find(root, (K) key);

        return node == null ? null : node.value;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public V put(K key, V value) {
        Objects.requireNonNull(key);

        Node<K, V> current = find(root, key);
        V oldValue = null;

        if (Objects.nonNull(current)) {
            oldValue = current.value;
            current.value = value;
        } else {
            root = put(root, key, value);
            size++;
        }

        return oldValue;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public V remove(Object key) {
        Objects.requireNonNull(key);

        Node<K, V>[] value = new Node[1];
        root = remove(root, (K) key, value);

        if (Objects.nonNull(value[0])) {
            size--;
            return value[0].value;
        }

        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void putAll(Map<? extends K, ? extends V> m) {
        Objects.requireNonNull(m);

        for (Entry<? extends K, ? extends V> entry : m.entrySet()) {
            put(entry.getKey(), entry.getValue());
        }

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void clear() {
        root = null;
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
    public Set<Entry<K, V>> entrySet() {
        return new CustomEntrySet();
    }

    // ---------------------------------------------------
    // ---------- Private and auxiliary methods ----------
    // ---------------------------------------------------

    private boolean findNodeByValue(Node<K, V> node, V value) {

        if (Objects.isNull(node)) {
            return false;
        }

        if ((value == null && node.value == null) || (value != null && value.equals(node.value))) {
            return true;
        }

        if (findNodeByValue(node.left, value) ||
                findNodeByValue(node.right, value)) {
            return true;
        }

        return false;
    }

    private Node<K, V> put(Node<K, V> node, K key, V value) {
        if (node == null) {
            return new Node<>(key, value);
        }
        if (node.key.equals(key)) {
            node.value = value;
        } else if (node.key.compareTo(key) > 0) {
            node.left = put(node.left, key, value);
        } else {
            node.right = put(node.right, key, value);
        }
        return node;
    }

    private Node<K, V> find(Node<K, V> node, K key) {
        if (node == null) {
            return null;
        }
        if (node.key.equals(key)) {
            return node;
        } else if (node.key.compareTo(key) > 0) {
            return find(node.left, key);
        } else {
            return find(node.right, key);
        }
    }

    private Node<K, V> remove(Node<K, V> current, K key, Node<K, V>[] value) {
        if (current == null) {
            return null;
        }

        int compareResult = current.key.compareTo(key);

        if (compareResult > 0) {
            current.left = remove(current.left, key, value);
        } else if (compareResult < 0) {
            current.right = remove(current.right, key, value);
        } else {
            value[0] = current;
            if (current.left == null) {
                current = current.right;
            } else if (current.right == null) {
                current =  current.left;
            } else {
                Node<K, V> oldCurrent = current;
                current = findMin(current.right);
                current.right = removeMin(oldCurrent.right);
                current.left = oldCurrent.left;
            }
        }

        return current;
    }

    private Node<K, V> removeMin(Node<K, V> node) {
        if (Objects.isNull(node.left)) {
            return node.right;
        }

        node.left = removeMin(node.left);

        return node;
    }

    private Node<K, V> findMin(Node<K, V> node) {

        while (Objects.nonNull(node.left)) {
            node = node.left;
        }

        return node;
    }

    private class Node<K extends Comparable<K>, V> implements Map.Entry<K, V> {
        private final K key;
        private V value;
        private Node<K, V> left;
        private Node<K, V> right;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
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
            V oldValue = value;
            this.value = value;

            return oldValue;
        }
    }

    final class CustomEntrySet extends AbstractSet<Entry<K, V>> {

        @Override
        public Iterator<Entry<K, V>> iterator() {
            return new EntryIterator();
        }

        @Override
        public int size() {
            return CustomTreeMap.this.size();
        }
    }

    final class CustomKeySet extends AbstractSet<K> {

        @Override
        public Iterator<K> iterator() {
            return new KeyIterator();
        }

        @Override
        public int size() {
            return CustomTreeMap.this.size();
        }
    }

    final class CustomValueSet extends AbstractSet<V> {

        @Override
        public Iterator<V> iterator() {
            return new ValueIterator();
        }

        @Override
        public int size() {
            return CustomTreeMap.this.size();
        }
    }

    abstract class CommonIterator {

        Entry<K, V>[] data = new Entry[size];
        int current;
        int next;

        public CommonIterator() {
            traverseNodes(root, data, 0);

            current = -1;
            next = 0;
        }

        private int traverseNodes(Node<K, V> current, Entry<K, V>[] data, int index) {
            if (current == null) {
                return index;
            }

            index = traverseNodes(current.left, data, index);
            data[index++] = current;
            index = traverseNodes(current.right, data, index);

            return index;
        }

        public boolean hasNext() {
            return next < data.length;
        }

        public void remove() {
            if (current < 0) {
                throw new IllegalStateException();
            }

            CustomTreeMap.this.remove(data[current].getKey());
            current = -1;
        }

    }

    final class EntryIterator extends CommonIterator implements Iterator<Entry<K, V>> {

        @Override
        public Entry<K, V> next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }

            current = next;
            next++;

            return data[current];
        }
    }

    final class KeyIterator extends CommonIterator implements Iterator<K> {

        @Override
        public K next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }

            current = next;
            next++;

            return data[current].getKey();
        }
    }

    final class ValueIterator extends CommonIterator implements Iterator<V> {

        @Override
        public V next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }

            current = next;
            next++;

            return data[current].getValue();
        }
    }
}