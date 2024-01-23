package com.test.rxjava.ch06;

import com.test.rxjava.DebugSubscriber;
import io.reactivex.Flowable;

public class DoOnSubscribeSample {
    public static void main(String[] args) {
        Flowable.range(1, 5)
                .doOnSubscribe(s -> System.out.println(s))
                .subscribe(new DebugSubscriber<>());
    }
}
