package com.konserval.ratelimiter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SlidingWindowRateLimiterTest {

    private static final int WINDOW_SEC = 3;
    private static final int MAX_EVENT_COUNT = 3;
    private RateLimiter limiter;


    @BeforeEach
    public void setUp() {
        limiter = new SlidingWindowRateLimiter(MAX_EVENT_COUNT, WINDOW_SEC);
    }

    @Test
    public void testRateLimitNoTimings() {
        for (int i = 0; i < MAX_EVENT_COUNT; i++) {
            assertTrue(limiter.isUnderLimit());
        }
        assertFalse(limiter.isUnderLimit());
    }

    // Never do test which relly n timings
    @Test
    public void testRateLimitWithTimings() throws InterruptedException {
        var sleepDurationMilis = WINDOW_SEC * 1000 / MAX_EVENT_COUNT;
        for (int i = 0; i < MAX_EVENT_COUNT; i++) {
            Thread.sleep(Duration.ofMillis(sleepDurationMilis));
            assertTrue(limiter.isUnderLimit());
        }
        assertFalse(limiter.isUnderLimit());
        Thread.sleep(Duration.ofMillis(sleepDurationMilis));
        assertTrue(limiter.isUnderLimit());
    }


}
