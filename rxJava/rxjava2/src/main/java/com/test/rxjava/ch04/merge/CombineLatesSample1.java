package com.test.rxjava.ch04.merge;

import com.test.rxjava.DebugSubscriber;
import io.reactivex.Flowable;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 데이터 중복에 대한 부분이 존재함, 이거 사용하나?
 */
public class CombineLatesSample1 {
    public static void main(String[] args) throws InterruptedException {
        Flowable<Long> flowable1 = Flowable.interval(300L, TimeUnit.MILLISECONDS)
                .take(5);

        Flowable<Long> flowable2 = Flowable.interval(500L, TimeUnit.MILLISECONDS)
                .take(3)
                .map(data -> data + 100);

        Flowable<List<Long>> result = Flowable.combineLatest(flowable1, flowable2, (d1, d2) -> Arrays.asList(d1, d2));
        result.subscribe(new DebugSubscriber<>());
        Thread.sleep(2000L);
    }
}
