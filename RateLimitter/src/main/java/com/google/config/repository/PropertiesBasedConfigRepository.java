package com.google.config.repository;

import com.google.config.RateLimitConfig;
import com.google.rateLimiter.RateLimitAlgorithm;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class PropertiesBasedConfigRepository implements RateLimitConfigRepository{

    private Map<String, RateLimitConfig> resources;
    private RateLimitConfig defaultConfig;

    public PropertiesBasedConfigRepository() {
        resources = new ConcurrentHashMap<>();
        // setting up new config for "login"
        resources.put("login", new RateLimitConfig(1, Duration.ofSeconds(10),
                RateLimitAlgorithm.FIXED_WINDOW_COUNTER));

        // setting up new config for "post"
        resources.put("post", new RateLimitConfig(3, Duration.ofSeconds(10),
                RateLimitAlgorithm.FIXED_WINDOW_COUNTER));

        // setting up new config for "health-check"
        resources.put("health-check", new RateLimitConfig(1, Duration.ofSeconds(10),
                RateLimitAlgorithm.FIXED_WINDOW_COUNTER));

        defaultConfig = new RateLimitConfig(2, Duration.ofSeconds(10),
                RateLimitAlgorithm.FIXED_WINDOW_COUNTER);
    }

    @Override
    public RateLimitConfig getConfig(String resource) {
        RateLimitConfig rateLimitConfig = resources.get(resource);
        if(rateLimitConfig == null) {
            return defaultConfig;
        }
        return rateLimitConfig;
    }

    @Override
    public void updateConfig(String resource, RateLimitConfig config) {
        resources.put(resource, config);
    }

    @Override
    public Map<String, RateLimitConfig> getAllConfigs() {
        return resources;
    }

    @Override
    public void deleteConfig(String resource) {
        resources.remove(resource);
    }
}
