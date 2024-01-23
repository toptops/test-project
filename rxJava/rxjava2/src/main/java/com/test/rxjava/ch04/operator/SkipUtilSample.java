package com.test.rxjava.ch04.operator;

import com.test.rxjava.DebugSubscriber;
import io.reactivex.Flowable;

import java.util.concurrent.TimeUnit;

/**
 * Timer같은 뭔가 알람 같은 느낌이다. Flowable 통지 혹은 조건에 따른 통지에 따라 이후 메인 통지하기에..
 */
public class SkipUtilSample {
    public static void main(String[] args) throws InterruptedException {
        Flowable<Long> flowable = Flowable.interval(300L, TimeUnit.MILLISECONDS)
                .skipUntil(Flowable.timer(1000L, TimeUnit.MILLISECONDS));

        flowable.subscribe(new DebugSubscriber<>());
        Thread.sleep(2000L);
    }
}
