package com.test.rxjava.ch04.operator;

import com.test.rxjava.DebugSubscriber;
import io.reactivex.Flowable;

import java.util.concurrent.TimeUnit;

/**
 * 당첨 같은곳에 쓰일수 있을듯
 */
public class TakeWhileSample {
    public static void main(String[] args) throws InterruptedException {
        Flowable<Long> flowable = Flowable.interval(300L, TimeUnit.MILLISECONDS)
                .takeWhile(data -> data!=3);
        flowable.subscribe(new DebugSubscriber<>());
        Thread.sleep(2000L);
    }
}
