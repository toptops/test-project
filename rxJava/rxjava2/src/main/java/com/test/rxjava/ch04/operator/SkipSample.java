package com.test.rxjava.ch04.operator;

import com.test.rxjava.DebugSubscriber;
import io.reactivex.Flowable;

import java.util.concurrent.TimeUnit;

/**
 * 앞에서부터 스킵한다. 흠...
 * 완료 통보를 안하네?
 */
public class SkipSample {
    public static void main(String[] args) throws InterruptedException {
        Flowable<Long> flowable = Flowable.interval(1000L, TimeUnit.MILLISECONDS)
                .skip(2);
        flowable.subscribe(new DebugSubscriber<>());
        Thread.sleep(5000L);
    }
}
