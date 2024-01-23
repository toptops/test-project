package com.test.rxjava.ch04.convert;

import com.test.rxjava.DebugSubscriber;
import io.reactivex.Flowable;

/**
 * Stream 함수와 똑같은 기능을 한다.
 */
public class MapSample {
    public static void main(String[] args) {
        Flowable<String> flowable = Flowable.just("A", "B", "C", "D", "E")
                .map(data -> data.toLowerCase());

        flowable.subscribe(new DebugSubscriber<>());
    }
}
