package com.test.rxjava.ch04.operator;

import com.test.rxjava.DebugSubscriber;
import io.reactivex.Flowable;

import java.util.concurrent.TimeUnit;

/**
 * 이건 괜찮네.. 비동기로 시간이나 개수 두고 처리되는거만 통지하면 되니깐 좋은듯
 */
public class TakeLastSample1 {
    public static void main(String[] args) throws InterruptedException {
        Flowable<Long> flowable = Flowable.interval(800, TimeUnit.MILLISECONDS)
                .take(5)
                .takeLast(2);

        flowable.subscribe(new DebugSubscriber<>());
        Thread.sleep(5000);
    }
}
