package com.test.rxjava.ch04.convert;

import com.test.rxjava.ch04.DebugSubscriber;
import io.reactivex.Flowable;

import java.util.concurrent.TimeUnit;

public class ConcatMapEagerDelayErrorSample {
    public static void main(String[] args) throws InterruptedException {
        Flowable<String> flowable = Flowable.range(10, 3)
                .concatMapEagerDelayError(
                        s -> Flowable.interval(500L, TimeUnit.MILLISECONDS)
                                .take(3)
                                .doOnNext(data -> {
                                    if(data == 1 && s == 11) {
                                        throw new Exception("예외 발생");
                                    }
                                })
                                .map(data -> "[" + s + "]: " + data),
                        true
                );
        flowable.subscribe(new DebugSubscriber<>());
        Thread.sleep(4_000);
    }
}
