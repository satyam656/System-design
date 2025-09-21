package com.google.service;

import com.google.config.RateLimitConfig;
import com.google.config.repository.RateLimitConfigRepository;
import com.google.rateLimiter.RateLimitResult;
import com.google.rateLimiter.RateLimiter;
import com.google.rateLimiter.factory.RateLimiterFactory;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class RateLimiterService {
    private final Map<String, RateLimiter> rateLimiters;
    private final RateLimiterFactory factory;
    private final RateLimitConfigRepository configRepository;

    public RateLimiterService(RateLimiterFactory factory, RateLimitConfigRepository configRepository) {
        this.factory = factory;
        this.configRepository = configRepository;
        this.rateLimiters = new ConcurrentHashMap<>();
    }

    public boolean isRequestAllowed(String clientId, String resource) {
        RateLimiter rateLimiter = getRateLimiter(resource);
        return rateLimiter.isAllowed(clientId, resource);
    }

    public RateLimitResult getRateLimitInfo(String clientId, String resource) {
        RateLimiter rateLimiter = rateLimiters.get(resource);
        return rateLimiter.getAllowanceInfo(clientId, resource);
    }

    private RateLimiter getRateLimiter(String resource) {
        return rateLimiters.computeIfAbsent(resource, r-> {
            RateLimitConfig config = configRepository.getConfig(r);
            return factory.createRateLimiter(config);
        });
    }
}
