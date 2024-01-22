package com.test.rxjava.ch04.generator;

import com.test.rxjava.ch04.DebugSubscriber;
import io.reactivex.Flowable;

import java.util.concurrent.Callable;

public class FromCallableSample {
    public static void main(String[] args) {
        Flowable<Long> flowable = Flowable.fromCallable(() -> System.currentTimeMillis());
        flowable.subscribe(new DebugSubscriber<>());
    }
}
