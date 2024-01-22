package com.test.rxjava.ch04.generator;

import com.test.rxjava.ch04.DebugSubscriber;
import io.reactivex.Flowable;

public class JustSample {
    public static void main(String[] args) {
        Flowable<String> flowable = Flowable.just("A", "B", "C", "D", "E");
        flowable.subscribe(new DebugSubscriber<>());
    }
}
