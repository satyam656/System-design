package com.google.rateLimiter;

public interface RateLimiter {
    boolean isAllowed(String clientId, String resource);
//    boolean isAllowed(String clientId, String resource, int tokens);
    RateLimitResult getAllowanceInfo(String cleintId, String resource);
    void reset(String clientId, String resource);
}
