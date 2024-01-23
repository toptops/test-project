package com.test.rxjava.ch04.operator;

import com.test.rxjava.DebugMaybeObserver;
import io.reactivex.Flowable;
import io.reactivex.Maybe;

import java.util.concurrent.TimeUnit;

public class ElementAtSample {
    public static void main(String[] args) throws InterruptedException {
        Maybe<Long> maybe = Flowable.interval(100L, TimeUnit.MILLISECONDS)
                .elementAt(3);
        maybe.subscribe(new DebugMaybeObserver<>());
        Thread.sleep(1000L);
    }
}
