package com.test.rxjava.ch04.operator;

import com.test.rxjava.DebugSubscriber;
import io.reactivex.Flowable;

public class DistinctSample2 {
    public static void main(String[] args) {
        Flowable<String> flowable = Flowable.just("A", "a", "B", "b", "A", "a", "B", "b")
                .distinct(data -> data.toUpperCase());
        flowable.subscribe(new DebugSubscriber<>());
    }
}
