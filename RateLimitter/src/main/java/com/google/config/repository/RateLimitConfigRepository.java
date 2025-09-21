package com.google.config.repository;

import com.google.config.RateLimitConfig;

import java.util.Map;

public interface RateLimitConfigRepository {
    RateLimitConfig getConfig(String resource);
    void updateConfig(String resource, RateLimitConfig config);
    Map<String, RateLimitConfig> getAllConfigs();
    void deleteConfig(String resource);
}
