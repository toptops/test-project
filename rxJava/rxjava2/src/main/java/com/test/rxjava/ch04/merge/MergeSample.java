package com.test.rxjava.ch04.merge;

import com.test.rxjava.DebugSubscriber;
import io.reactivex.Flowable;

import java.util.concurrent.TimeUnit;

public class MergeSample {
    public static void main(String[] args) throws InterruptedException {
        Flowable<Long> flowable1 = Flowable.interval(300L, TimeUnit.MILLISECONDS)
                .take(5);

        Flowable<Long> flowable2 = Flowable.interval(500L, TimeUnit.MILLISECONDS)
                .take(2)
                .map(data -> data + 100);

        Flowable<Long> result = Flowable.merge(flowable1, flowable2);
        result.subscribe(new DebugSubscriber<>());
        Thread.sleep(2000);
    }
}
