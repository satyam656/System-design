package com.google.rateLimiter;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class TokenBucketRateLimiter implements RateLimiter{
    private final int capacity;
    private final double refillRate; // Tokens per second
    private final Map<String, BucketState> buckets;
    private final ReadWriteLock lock = new ReentrantReadWriteLock();

    private static class BucketState {
        private double tokens;
        private long lastRefillTime;

        BucketState(int capacity) {
            this.tokens = capacity;
            this.lastRefillTime = System.currentTimeMillis();
        }
    }
    public TokenBucketRateLimiter(int capacity, double refillRate) {
        this.capacity = capacity;
        this.refillRate = refillRate;
        this.buckets = new ConcurrentHashMap<>();
    }


    @Override
    public boolean isAllowed(String clientId, String resource) {
        return isAllowed(clientId, resource, 1);
    }

    public boolean isAllowed(String clientId, String resource, int requiredToken) {
        String key = generateKey(clientId, resource);
        lock.writeLock().lock();

        try {
            BucketState bucketState = buckets.computeIfAbsent(key, k-> new BucketState(capacity));
            refillBucket(bucketState);
            if(bucketState.tokens >= requiredToken) {
                bucketState.tokens -= requiredToken;
                return true;
            }
            return false;
        } finally {
            lock.writeLock().unlock();
        }
    }

    private void refillBucket(BucketState bucketState) {
        long now = System.currentTimeMillis();
        double tokensToAdd = (now - bucketState.lastRefillTime) / 1000.0 * refillRate;
        bucketState.tokens = Math.min(capacity, bucketState.tokens + tokensToAdd);
        bucketState.lastRefillTime = now;
    }

    @Override
    public RateLimitResult getAllowanceInfo(String cleintId, String resource) {
        String key = generateKey(cleintId, resource);

        lock.readLock().lock();
        try {
            BucketState bucketState = buckets.get(key);
            if(bucketState == null) {
                return new RateLimitResult(true, capacity, 0, capacity);
            }
            long nextRefillTime = (long) (bucketState.lastRefillTime +
                    (capacity - bucketState.tokens) / refillRate * 1000);
            return new RateLimitResult(bucketState.tokens >= 1, (int) bucketState.tokens, nextRefillTime, capacity);
        } finally {
            lock.readLock().unlock();
        }
    }

    @Override
    public void reset(String clientId, String resource) {
        buckets.clear();
    }

    private String generateKey(String clientId, String resource) {
        return clientId + ":" + resource;
    }
}
