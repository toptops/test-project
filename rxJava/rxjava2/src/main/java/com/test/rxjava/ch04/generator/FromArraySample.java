package com.test.rxjava.ch04.generator;

import com.test.rxjava.DebugSubscriber;
import io.reactivex.Flowable;

public class FromArraySample {
    public static void main(String[] args) {
        Flowable<String> flowable = Flowable.fromArray("A", "B", "C", "D", "E");
        flowable.subscribe(new DebugSubscriber<>());
    }
}
