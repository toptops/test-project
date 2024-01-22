package com.test.rxjava.ch04.convert;

import com.test.rxjava.ch04.DebugSubscriber;
import io.reactivex.Flowable;

public class FlatMapSample3 {
    public static void main(String[] args) {
        Flowable<Integer> original = Flowable.just(1, 2, 3, 4, 5)
                .map(data -> 10/data);

        Flowable<Integer> flowable = original.flatMap(
                data -> Flowable.just(data),
                error -> Flowable.just(-1),
                () -> Flowable.just(100)
        );

        flowable.subscribe(new DebugSubscriber<>());
    }
}
