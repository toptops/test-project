package com.test.rxjava.ch04.generator;

import com.test.rxjava.ch04.DebugSubscriber;
import io.reactivex.Flowable;

import java.time.LocalTime;

/**
 * 구독할때 마다 새로운 Flowable 생성...?
 */
public class DeferSample {
    public static void main(String[] args) throws Exception{
        Flowable<LocalTime> flowable = Flowable.defer(() -> Flowable.just(LocalTime.now()));
        flowable.subscribe(new DebugSubscriber<>("No. 1"));

        Thread.sleep(2000);
        flowable.subscribe(new DebugSubscriber<>("No. 2"));
    }
}
