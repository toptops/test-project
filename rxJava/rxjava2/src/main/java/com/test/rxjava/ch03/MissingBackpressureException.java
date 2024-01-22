package com.test.rxjava.ch03;

import io.reactivex.Flowable;
import io.reactivex.schedulers.Schedulers;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.concurrent.TimeUnit;

/**
 * 흠.. rxjava를 어디다가 응용해야되나..
 */
public class MissingBackpressureException {
    public static void main(String[] args) throws InterruptedException {
        Flowable<Long> flowable = Flowable.interval(10L, TimeUnit.MILLISECONDS)
                .doOnNext(value -> System.out.println("emit: " + value));

        flowable.observeOn(Schedulers.computation())
                .subscribe(new Subscriber<Long>() {
                    @Override
                    public void onSubscribe(Subscription s) {
                        s.request(Long.MAX_VALUE);
                    }

                    @Override
                    public void onNext(Long aLong) {
                        try {
                            System.out.println("wating.....");
                            Thread.sleep(1000L);
                        } catch (InterruptedException e) {}
                        System.out.println("received: " + aLong);
                    }

                    @Override
                    public void onError(Throwable t) {
                        System.out.println("에러 : " + t);
                    }

                    @Override
                    public void onComplete() {
                        System.out.println("완료!");
                    }
                });
        Thread.sleep(5000L);
    }
}
