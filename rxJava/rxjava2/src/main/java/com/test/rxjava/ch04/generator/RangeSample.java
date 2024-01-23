package com.test.rxjava.ch04.generator;

import com.test.rxjava.DebugSubscriber;
import io.reactivex.Flowable;

public class RangeSample {
    public static void main(String[] args) {
        Flowable<Integer> flowable = Flowable.range(10, 3);
        flowable.subscribe(new DebugSubscriber<>());
    }
}
