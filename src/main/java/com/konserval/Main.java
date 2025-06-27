package com.konserval;

import com.konserval.ratelimiter.RateLimiter;
import com.konserval.ratelimiter.SlidingWindowRateLimiter;

import java.time.Duration;

public class Main {
    public static void main(String[] args) throws InterruptedException {

        RateLimiter limiter = new SlidingWindowRateLimiter(3, 3);

        for (int i = 1; i <= 30; i++) {
            limiter.isUnderLimit();
            Thread.sleep(Duration.ofMillis(900));
        }
    }
}