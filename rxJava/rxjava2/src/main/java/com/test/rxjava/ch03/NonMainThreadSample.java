package com.test.rxjava.ch03;

import io.reactivex.Flowable;
import io.reactivex.subscribers.ResourceSubscriber;

import java.util.concurrent.TimeUnit;

public class NonMainThreadSample {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("Start!!");
        Flowable.interval(300L, TimeUnit.MILLISECONDS)
                .subscribe(new ResourceSubscriber<Long>() {

                    @Override
                    public void onNext(Long data) {
                        String threadName = Thread.currentThread().getName();
                        System.out.println(threadName + ": " + data);
                    }

                    @Override
                    public void onError(Throwable t) {
                        t.printStackTrace();
                    }

                    @Override
                    public void onComplete() {
                        String threadName = Thread.currentThread().getName();
                        System.out.println(threadName + ": 완료");
                    }
                });
        System.out.println("End!!");
        Thread.sleep(1000);
    }
}
