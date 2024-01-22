package com.test.rxjava.ch04.convert;

import com.test.rxjava.ch04.DebugSubscriber;
import io.reactivex.Flowable;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class BufferSample {
    public static void main(String[] args) throws InterruptedException {
        Flowable<List<Long>> flowable = Flowable.interval(100L, TimeUnit.MILLISECONDS)
                .take(10)
                .buffer(3);

        flowable.subscribe(new DebugSubscriber<>());
        Thread.sleep(3000L);
    }
}
