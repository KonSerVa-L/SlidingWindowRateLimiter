package com.konserval.ratelimiter;

import lombok.AllArgsConstructor;

import java.util.LinkedList;
import java.util.List;

@AllArgsConstructor
public class SlidingWindowRateLimiter implements RateLimiter {

    private int maxEventCount;
    private int windowSec;
    private final List<Long> events = new LinkedList<>();


    @Override
    public boolean isUnderLimit() {

        var currentMoment = System.currentTimeMillis();
        removeOldEvents(currentMoment);
        if (events.size() < maxEventCount) {
            events.addLast(currentMoment);
            System.out.println("ALLOWED");
            return true;
        } else {
            System.out.println("DECLINED");
            return false;
        }
    }

    private void removeOldEvents(long currentMoment) {
        while (!events.isEmpty() && events.getFirst() < (currentMoment - windowSec * 1000L)) {
            events.removeFirst();
        }
    }


}
