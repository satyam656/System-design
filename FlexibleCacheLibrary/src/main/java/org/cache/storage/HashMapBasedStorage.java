package org.cache.storage;

import org.cache.exception.NotFoundException;
import org.cache.exception.StorageFullException;

import java.util.HashMap;
import java.util.Map;

public class HashMapBasedStorage<Key, Value> implements Storage<Key, Value>{

    private Map<Key, Value> storage;
    private final Integer capacity;

    public HashMapBasedStorage(Integer capacity) {
        this.capacity = capacity;
        this.storage = new HashMap<>();
    }

    @Override
    public void add(Key key, Value value) throws StorageFullException {
        if(isStorageFull()) throw new StorageFullException("Capacity full ... ");
        storage.put(key, value);
    }

    @Override
    public Value get(Key key) throws NotFoundException {
        if(!storage.containsKey(key)) throw new NotFoundException(key + " doesn't exist in cache");
        return storage.get(key);
    }

    @Override
    public void remove(Key key) throws NotFoundException {
        if(!storage.containsKey(key)) throw new NotFoundException(key + " doesn't found in cache");
        storage.remove(key);
    }

    @Override
    public boolean containsKey(Key key) {
        return storage.containsKey(key);
    }

    @Override
    public int getCurrentSize() {
        return storage.size();
    }

    @Override
    public int getCapacity() {
        return this.capacity;
    }

    private boolean isStorageFull() {
        return this.capacity == this.storage.size();
    }
}
