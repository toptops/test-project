package com.test.rxjava.ch04.merge;

import com.test.rxjava.DebugSubscriber;
import io.reactivex.Flowable;

import java.util.concurrent.TimeUnit;

public class StartWithSample {
    public static void main(String[] args) throws InterruptedException {
        Flowable<Long> flowable = Flowable.interval(300, TimeUnit.MILLISECONDS)
                .take(5);

        Flowable<Long> other = Flowable.interval(500L, TimeUnit.MILLISECONDS)
                .take(2)
                .map(data -> data + 100);

        Flowable<Long> result = flowable.startWith(other);
        result.subscribe(new DebugSubscriber<>());
        Thread.sleep(3000);
    }
}
