package com.test.rxjava.ch04.convert;

import com.test.rxjava.ch04.DebugSubscriber;
import io.reactivex.Flowable;

import java.util.concurrent.TimeUnit;

public class ConcatMapSample {
    public static void main(String[] args) throws InterruptedException {
        Flowable<String> flowable = Flowable.range(10, 3)
                .concatMap(
                        sourceData -> Flowable.interval(500L, TimeUnit.MILLISECONDS)
                                .take(2)
                                .map(data -> {
                                    long time = System.currentTimeMillis();
                                    return time + "ms: [" + sourceData + "] " + data;
                                })
                );

        flowable.subscribe(new DebugSubscriber<>());
        Thread.sleep(4000L);
    }
}
