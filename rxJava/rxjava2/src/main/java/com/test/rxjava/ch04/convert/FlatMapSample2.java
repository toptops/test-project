package com.test.rxjava.ch04.convert;

import com.test.rxjava.ch04.DebugSubscriber;
import io.reactivex.Flowable;

import java.util.concurrent.TimeUnit;

public class FlatMapSample2 {
    public static void main(String[] args) throws InterruptedException {
        Flowable<String> flowable = Flowable.range(1, 3)
                .flatMap(data -> Flowable.interval(100L, TimeUnit.MILLISECONDS).take(3),
                        (s, n) -> "[" + s + "] " + n);

        flowable.subscribe(new DebugSubscriber<>());
        Thread.sleep(1000L);
    }
}
