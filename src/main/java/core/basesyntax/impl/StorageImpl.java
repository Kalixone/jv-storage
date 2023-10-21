package core.basesyntax.impl;

import core.basesyntax.Storage;
import java.util.Objects;

public class StorageImpl<K, V> implements Storage<K, V> {
    private static final int MAX_SIZE = 10;
    private Object[] keys;
    private Object[] values;
    private int size;

    public StorageImpl() {
        keys = new Object[MAX_SIZE];
        values = new Object[MAX_SIZE];
        size = 0;
    }

    @Override
    public void put(K key, V value) {
        if (key == null) {
            for (int i = 0; i < size; i++) {
                if (keys[i] == null) {
                    values[i] = value;
                    return;
                }
            }
            if (size >= MAX_SIZE) {
                throw new IllegalStateException("Storage is full");
            }
            keys[size] = null;
            values[size] = value;
            size++;
        } else {
            for (int i = 0; i < size; i++) {
                if (Objects.equals(key, keys[i])) {
                    values[i] = value;
                    return;
                }
            }
            if (size >= MAX_SIZE) {
                throw new IllegalStateException("Storage is full");
            }
            keys[size] = key;
            values[size] = value;
            size++;
        }
    }

    @Override
    public V get(K key) {
        for (int i = 0; i < size; i++) {
            if (Objects.equals(key, keys[i])) {
                return (V) values[i];
            }
        }
        return null;
    }

    @Override
    public int size() {
        return size;
    }
}
