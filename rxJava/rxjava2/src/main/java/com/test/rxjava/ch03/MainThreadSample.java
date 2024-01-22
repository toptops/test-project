package com.test.rxjava.ch03;

import io.reactivex.Flowable;
import io.reactivex.subscribers.ResourceSubscriber;

public class MainThreadSample {
    public static void main(String[] args) {
        System.out.println("Start!!");
        Flowable.just(1, 2, 3)
                .subscribe(new ResourceSubscriber<Integer>() {
                    @Override
                    public void onNext(Integer integer) {
                        String threadName = Thread.currentThread().getName();
                        System.out.println(threadName + ": " + integer);
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
    }
}
