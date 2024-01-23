package com.test.rxjava.ch04.aggregate;

import com.test.rxjava.DebugSubscriber;
import io.reactivex.Flowable;

public class ScanSample {
    public static void main(String[] args) {
        Flowable<Integer> flowable = Flowable.just(1, 10, 100, 1000, 10000)
                .scan(0, (s, d) -> s+d);
        flowable.subscribe(new DebugSubscriber<>());
    }
}
