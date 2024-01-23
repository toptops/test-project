package com.test.rxjava.ch04.util;

import com.test.rxjava.DebugSubscriber;
import io.reactivex.Flowable;

import java.util.concurrent.TimeUnit;

public class RepeatUtilSample {
    public static void main(String[] args) throws InterruptedException {
        final long startTime = System.currentTimeMillis();

        Flowable<Long> flowable = Flowable.interval(100L, TimeUnit.MILLISECONDS)
                .take(3)
                .repeatUntil(() -> {
                    System.out.println("called");
                    return System.currentTimeMillis() - startTime < 500L;
                });

        flowable.subscribe(new DebugSubscriber<>());
        Thread.sleep(1000L);
    }
}
