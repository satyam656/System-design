package com.google.rateLimiter;

public class RateLimitResult {
    private final boolean allowed;
    private final int remainingTokens;
    private final long resetTimeMillis;
    private final int totalLimit;

    public RateLimitResult(boolean allowed, int remainingTokens, long resetTimeMillis, int totalLimit) {
        this.allowed = allowed;
        this.remainingTokens = remainingTokens;
        this.resetTimeMillis = resetTimeMillis;
        this.totalLimit = totalLimit;
    }

    public boolean isAllowed() {
        return allowed;
    }

    public int getRemainingTokens() {
        return remainingTokens;
    }

    public long getResetTimeMillis() {
        return resetTimeMillis;
    }

    public int getTotalLimit() {
        return totalLimit;
    }
}
