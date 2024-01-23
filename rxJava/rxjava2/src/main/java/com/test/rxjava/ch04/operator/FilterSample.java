package com.test.rxjava.ch04.operator;

import com.test.rxjava.DebugSubscriber;
import io.reactivex.Flowable;

import java.util.concurrent.TimeUnit;

/**
 * 흠...? Stream 그냥 결합된 것 뿐이군
 */
public class FilterSample {
    public static void main(String[] args) throws InterruptedException {
        Flowable<Long> flowable = Flowable.interval(300L, TimeUnit.MILLISECONDS)
                .filter(data -> data%2 == 0);

        flowable.subscribe(new DebugSubscriber<>());
        Thread.sleep(3000L);
    }
}
