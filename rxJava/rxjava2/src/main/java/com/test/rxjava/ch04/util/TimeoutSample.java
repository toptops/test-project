package com.test.rxjava.ch04.util;

import com.test.rxjava.DebugSubscriber;
import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;

import java.util.concurrent.TimeUnit;

public class TimeoutSample {
    public static void main(String[] args) throws InterruptedException {
        Flowable<Integer> flowable = Flowable.<Integer>create(e -> {
                e.onNext(1);
                e.onNext(2);
                try {
                    Thread.sleep(1200);
                } catch (InterruptedException t) {
                    e.onError(t);
                    return;
                }
                e.onNext(3);
                e.onComplete();
            }, BackpressureStrategy.BUFFER)
            .timeout(1000L, TimeUnit.MILLISECONDS, Flowable.just(3));

        flowable.subscribe(new DebugSubscriber<>());
        Thread.sleep(2000L);
    }
}
