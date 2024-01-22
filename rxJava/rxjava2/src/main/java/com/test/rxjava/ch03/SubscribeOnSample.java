package com.test.rxjava.ch03;

import io.reactivex.Flowable;
import io.reactivex.schedulers.Schedulers;

public class SubscribeOnSample {
    public static void main(String[] args) throws InterruptedException {
        Flowable.just(1, 2, 3, 4, 5)
                .subscribeOn(Schedulers.computation())
                .subscribeOn(Schedulers.io())
                .subscribeOn(Schedulers.single())
                .subscribe(data -> {
                    String threadName = Thread.currentThread().getName();
                    System.out.println(threadName + ": " + data);
                });
        Thread.sleep(1000);
    }
}
