package com.test.rxjava.ch03;

import io.reactivex.Flowable;

import java.util.concurrent.TimeUnit;

/**
 * 조심히 써야함.. 개수에 따라 쓰레드 각각 생성함 ㅅㅂ ㅋㅋㅋ
 */
public class FlatMapSample {
    public static void main(String[] args) throws InterruptedException {
        Flowable<String> flowable = Flowable.just("A", "B", "C")
                .flatMap(data -> Flowable.just(data).delay(1000L, TimeUnit.MILLISECONDS));

        flowable.subscribe(data -> {
            String threadName = Thread.currentThread().getName();
            System.out.println(threadName + " : " + data);
        });
        Thread.sleep(2000);
    }
}
