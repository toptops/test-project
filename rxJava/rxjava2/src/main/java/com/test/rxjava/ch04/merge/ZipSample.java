package com.test.rxjava.ch04.merge;

import com.test.rxjava.DebugSubscriber;
import io.reactivex.Flowable;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class ZipSample {
    public static void main(String[] args) throws InterruptedException {
        Flowable<Long> flowable1 = Flowable.interval(300L, TimeUnit.MILLISECONDS)
                .take(5);

        Flowable<Long> flowable2 = Flowable.interval(500L, TimeUnit.MILLISECONDS)
                .take(3)
                .map(d -> d + 100);

        // 흠? 각각의 결과를 합쳐서 새로운 객체를 도출하는건가?
        Flowable<List<Long>> result = Flowable.zip(flowable1, flowable2, (d1, d2) -> Arrays.asList(d1, d2));
        result.subscribe(new DebugSubscriber<>());
        Thread.sleep(2000L);
    }
}
