package com.test.rxjava.ch04.operator;

import com.test.rxjava.DebugSubscriber;
import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;

import java.util.concurrent.TimeUnit;

public class ThrottleWithTimeoutSample {
    public static void main(String[] args) {
        // 명시적으로 타입을 지정해줘야 한다.
        Flowable<String> flowable = Flowable.<String>create(
                e -> {
                    e.onNext("A");
                    Thread.sleep(1000L);

                    e.onNext("B");
                    Thread.sleep(300L);

                    e.onNext("C");
                    Thread.sleep(300L);

                    e.onNext("D");
                    Thread.sleep(1000L);

                    // 가장 마지막껀 그래도 출력이 되네
                    e.onNext("E");
                    Thread.sleep(100L);

                    e.onComplete();
                }, BackpressureStrategy.BUFFER
        ).throttleWithTimeout(500L, TimeUnit.MILLISECONDS);
        flowable.subscribe(new DebugSubscriber<>());
    }
}
