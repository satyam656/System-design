package org.cache;

import org.cache.policy.EvictionPolicy;
import org.cache.policy.LRUEvictionPolicy;
import org.cache.storage.HashMapBasedStorage;
import org.cache.storage.Storage;

public class Main {
    public static void main(String[] args) {
        System.out.println("Flexible Caching library");

        Storage<String, String> storage = new HashMapBasedStorage<>(5);
        EvictionPolicy<String> evictionPolicy = new LRUEvictionPolicy<>();
        Cache<String, String> cache = new Cache<>(storage, evictionPolicy);

        cache.put("1", "satyam");
        cache.put("2", "kumar");
        cache.put("3", "shubham");
        cache.put("4", "ram");
        cache.put("5", "lakshman");
        System.out.println(cache.get("1"));
        cache.put("6", "Amrita");
        System.out.println(cache.get("3"));
        cache.put("7", "kaur");
    }
}

