package com.test.rxjava.ch04.status;

import com.test.rxjava.DebugSingleObserver;
import io.reactivex.Flowable;
import io.reactivex.Single;

import java.util.concurrent.TimeUnit;

/**
 * 실데이터와 로그 데이터 비교할때 좋을듯
 */
public class SequenceEqualSample {
    public static void main(String[] args) throws InterruptedException {
        Flowable<Long> flowable1 = Flowable.interval(1000L, TimeUnit.MILLISECONDS)
                .take(3);

        Flowable<Long> flowable2 = Flowable.just(0L, 1L, 2L);

        Single<Boolean> single = Flowable.sequenceEqual(flowable1, flowable2);
        single.subscribe(new DebugSingleObserver<>());
        Thread.sleep(4000);
    }
}
