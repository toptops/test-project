package com.test.rxjava.ch04.operator;

import com.test.rxjava.DebugSubscriber;
import io.reactivex.Flowable;

import java.util.concurrent.TimeUnit;

public class TakeLastSample2 {
    public static void main(String[] args) throws InterruptedException {
        Flowable<Long> flowable = Flowable.interval(300L, TimeUnit.MILLISECONDS)
                .take(10)
                .takeLast(2, 1000L, TimeUnit.MILLISECONDS);
        flowable.subscribe(new DebugSubscriber<>());
        Thread.sleep(4000L);
    }
}
