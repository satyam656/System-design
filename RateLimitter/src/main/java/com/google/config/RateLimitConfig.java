package com.google.config;

import com.google.rateLimiter.RateLimitAlgorithm;

import java.time.Duration;

public class RateLimitConfig {
    private final int maxRequests;
    private final Duration timeWindow;
    private final RateLimitAlgorithm rateLimitAlgorithm;

    public RateLimitConfig(int maxRequests, Duration timeWindow, RateLimitAlgorithm rateLimitAlgorithm) {
        this.maxRequests = maxRequests;
        this.timeWindow = timeWindow;
        this.rateLimitAlgorithm = rateLimitAlgorithm;
    }

    public int getMaxRequests() {
        return maxRequests;
    }

    public Duration getTimeWindow() {
        return timeWindow;
    }

    public RateLimitAlgorithm getRateLimitAlgorithm() {
        return rateLimitAlgorithm;
    }

}
