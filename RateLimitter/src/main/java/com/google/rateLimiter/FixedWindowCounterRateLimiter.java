package com.google.rateLimiter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class FixedWindowCounterRateLimiter implements RateLimiter {
    private static final Logger logger = LoggerFactory.getLogger(FixedWindowCounterRateLimiter.class);

    private final int maxRequests;
    private final long windowSizeMillis;
    private final Map<String, WindowState> windows;
    private final ReadWriteLock lock = new ReentrantReadWriteLock();

    private static class WindowState {
        private int count;
        private long windowStart;

        WindowState(long windowStart) {
            this.count = 0;
            this.windowStart = windowStart;
        }
    }

    public FixedWindowCounterRateLimiter(int maxRequests, Duration windowSize) {
        this.maxRequests = maxRequests;
        this.windowSizeMillis = windowSize.toMillis();
        this.windows = new ConcurrentHashMap<>();
    }
    @Override
    public boolean isAllowed(String clientId, String resource) {
        String key = generateKey(clientId, resource);
        long now = System.currentTimeMillis();
        long currentWindow = now / windowSizeMillis;

        lock.writeLock().lock();
        try {
            WindowState window = windows.get(key);
            if(window == null || window.windowStart != currentWindow) {
                window = new WindowState(currentWindow);
                windows.put(key, window);
            }

            if(window.count < maxRequests) {
                window.count++;
                return true;
            }
            return false;
        }
        finally {
            lock.writeLock().unlock();
        }
    }

    @Override
    public RateLimitResult getAllowanceInfo(String cleintId, String resource) {
        String key = generateKey(cleintId, resource);
        long now = System.currentTimeMillis();
        long currentWindow = now / windowSizeMillis;

        lock.readLock().lock();
        try {
            WindowState window = windows.get(key);
            if(window == null || window.windowStart != currentWindow) {
                return new RateLimitResult(true, maxRequests, (currentWindow+1) * windowSizeMillis, maxRequests);
            }

            long resetTimeMillis = (window.windowStart+1) * windowSizeMillis;
            return new RateLimitResult(window.count < maxRequests, maxRequests - window.count, resetTimeMillis, maxRequests);
        }
        finally {
            lock.readLock().unlock();
        }
    }

    @Override
    public void reset(String clientId, String resource) {
        windows.clear();
    }

    private String generateKey(String clientId, String resource) {
        return clientId + ":" + resource;
    }
}
