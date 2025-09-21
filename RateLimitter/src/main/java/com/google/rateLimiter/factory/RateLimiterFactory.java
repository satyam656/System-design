package com.google.rateLimiter.factory;

import com.google.config.RateLimitConfig;
import com.google.rateLimiter.FixedWindowCounterRateLimiter;
import com.google.rateLimiter.RateLimitAlgorithm;
import com.google.rateLimiter.RateLimiter;
import org.springframework.stereotype.Component;

import java.time.Duration;

@Component
public class RateLimiterFactory {
    public RateLimiter createRateLimiter(RateLimitConfig config) {
        if(config.getRateLimitAlgorithm().equals(RateLimitAlgorithm.FIXED_WINDOW_COUNTER))
            return new FixedWindowCounterRateLimiter(config.getMaxRequests(), config.getTimeWindow());
        else
            return null;
    }
}
