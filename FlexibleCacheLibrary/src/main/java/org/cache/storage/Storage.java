package org.cache.storage;

import org.cache.exception.NotFoundException;
import org.cache.exception.StorageFullException;

public interface Storage<Key, Value> {
    public void add(Key key, Value value) throws StorageFullException;
    public Value get(Key key) throws NotFoundException;
    public void remove(Key key) throws NotFoundException;
    public boolean containsKey(Key key);
    public int getCurrentSize();
    public int getCapacity();
}
