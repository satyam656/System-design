package org.cache;

import org.cache.exception.NotFoundException;
import org.cache.exception.StorageFullException;
import org.cache.policy.EvictionPolicy;
import org.cache.storage.Storage;

public class Cache<Key, Value> {

    private final Storage<Key, Value> storage;
    private final EvictionPolicy<Key> evictionPolicy;

    public Cache(Storage<Key, Value> storage, EvictionPolicy<Key> evictionPolicy) {
        this.storage = storage;
        this.evictionPolicy = evictionPolicy;
    }

    public void put(Key key, Value value) {
        try {
            this.storage.add(key, value);
            this.evictionPolicy.keyAccessed(key);
        } catch (StorageFullException storageFullException) {
            System.out.println("Got storage full, Trying to evict...");
            Key keyToRemove = evictionPolicy.evictKey();
            System.out.println("Evicted key: " + keyToRemove);
            if(keyToRemove == null) {
                throw new RuntimeException("Unexpected State. Storage full and no key to evict");
            }

            if(this.storage.containsKey(keyToRemove))
                this.storage.remove(keyToRemove);
            put(key, value);
        }
    }
    public Value get(Key key) {
        try {
            Value value = this.storage.get(key);
            this.evictionPolicy.keyAccessed(key);
            return value;
        } catch (NotFoundException notFoundException) {
            System.out.println("Tried to access non existing key.");
            return null;
        }
    }
}


