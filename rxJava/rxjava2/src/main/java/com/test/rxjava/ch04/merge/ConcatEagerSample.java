package com.test.rxjava.ch04.merge;

import com.test.rxjava.DebugSubscriber;
import io.reactivex.Flowable;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class ConcatEagerSample {
    public static void main(String[] args) throws InterruptedException {
        Flowable<Long> flowable1 = Flowable.interval(200L, TimeUnit.MILLISECONDS)
                .take(5);

        Flowable<Long> flowable2 = Flowable.interval(500L, TimeUnit.MILLISECONDS)
                .take(5)
                .map(data -> data + 100);

        List<Flowable<Long>> sources = Arrays.asList(flowable1, flowable2);
        Flowable<Long> result = Flowable.concatEager(sources);
        result.subscribe(new DebugSubscriber<>());
        Thread.sleep(10000);
    }
}
