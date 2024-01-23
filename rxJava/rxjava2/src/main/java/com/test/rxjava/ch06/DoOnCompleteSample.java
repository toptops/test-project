package com.test.rxjava.ch06;

import com.test.rxjava.DebugSubscriber;
import io.reactivex.Flowable;

public class DoOnCompleteSample {
    public static void main(String[] args) {
        Flowable.range(1,5)
                .doOnComplete(() -> System.out.println("완료!"))
                .subscribe(new DebugSubscriber<>());
    }
}
