package com.test.rxjava.ch04.operator;

import com.test.rxjava.DebugSubscriber;
import io.reactivex.Flowable;

import java.util.concurrent.TimeUnit;

public class TakeUtilSample1 {
    public static void main(String[] args) throws InterruptedException {
        Flowable<Long> flowable = Flowable.interval(300L, TimeUnit.MILLISECONDS)
                .takeUntil(data -> data == 3);
        flowable.subscribe(new DebugSubscriber<>());
        Thread.sleep(2000L);
    }
}
