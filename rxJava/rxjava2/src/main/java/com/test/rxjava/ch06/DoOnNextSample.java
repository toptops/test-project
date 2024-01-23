package com.test.rxjava.ch06;

import com.test.rxjava.DebugSubscriber;
import io.reactivex.Flowable;

public class DoOnNextSample {
    public static void main(String[] args) {
        Flowable.range(1, 5)
                .doOnNext(d -> System.out.println("data : " + d))
                .filter(d -> d%2==0)
                .doOnNext(d -> System.out.println("change data : " + d))
                .subscribe(new DebugSubscriber<>());
    }
}
