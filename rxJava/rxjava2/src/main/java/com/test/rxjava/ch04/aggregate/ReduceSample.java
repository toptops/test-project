package com.test.rxjava.ch04.aggregate;

import com.test.rxjava.DebugSingleObserver;
import io.reactivex.Flowable;
import io.reactivex.Single;

/**
 * java Stream Collectors.reduce()랑 똑같다.
 */
public class ReduceSample {
    public static void main(String[] args) {
        Single<Integer> single = Flowable.just(1, 10, 100, 1000, 10000)
                .doOnNext(d -> System.out.println(d))
                .reduce(0, (sum, data) -> sum + data);
        single.subscribe(new DebugSingleObserver<>());
    }
}
