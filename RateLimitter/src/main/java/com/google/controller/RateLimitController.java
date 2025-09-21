package com.google.controller;

import com.google.service.RateLimiterService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;



@RestController
@RequestMapping("/rate-limit")
public class RateLimitController {

    private static final Logger logger = LoggerFactory.getLogger(RateLimitController.class);

    @Autowired
    private RateLimiterService rateLimiterService;

    @GetMapping("/login")
    public String login(@RequestParam("clientId") String clientId) {
        logger.info("New Request for clientId={} and resource={}", clientId, "login");
        boolean allowed = rateLimiterService.isRequestAllowed(clientId, "login");
        return allowed ? "Request allowed" : "Request blocked";
    }

    @GetMapping("/post")
    public String post(@RequestParam("clientId") String clientId) {
        logger.info("New Request arrived for client={} and resource={}", clientId, "post");
        boolean allowed = rateLimiterService.isRequestAllowed(clientId, "post");
        return allowed ? "Request allowed" : "Request blocked";
    }

    @GetMapping("/health-check")
    public String healthCheck(@RequestParam("clientId") String clientId) {
        logger.info("New Request arrived for client={} and resource={}", clientId, "health-check");
        boolean allowed = rateLimiterService.isRequestAllowed(clientId, "health-check");
        return allowed ? "Request allowed" : "Request blocked";
    }

    @GetMapping("rate-limit-info")
    public Object getRateLimitInfo(@RequestParam("clientId") String clientId, @RequestParam("resource") String resource) {
        logger.info("New Request arrived for rate-limit-info for client={} and resource={}", clientId, resource);
        return rateLimiterService.getRateLimitInfo(clientId, resource);
    }

}

