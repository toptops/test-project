package com.test.rxjava.ch04.status;

import com.test.rxjava.DebugSingleObserver;
import io.reactivex.Flowable;
import io.reactivex.Single;

import java.util.concurrent.TimeUnit;

/**
 * 엄..? 뭔가 좀 느리다?
 */
public class ContainsSample {
    public static void main(String[] args) throws InterruptedException {
        Single<Boolean> single = Flowable.interval(1000L, TimeUnit.MILLISECONDS)
                .contains(3L);
        single.subscribe(new DebugSingleObserver<>());
        Thread.sleep(5000L);
    }
}
