package com.test.rxjava.ch01;

import io.reactivex.Flowable;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.concurrent.TimeUnit;

public class SubscriptionCancelSample {
    public static void main(String[] args) throws InterruptedException {
        // 흠.. 내부 구현으로 무한으로 올라가는게 아니라 < 10까지만 올라가네..
        Flowable.interval(200L, TimeUnit.MILLISECONDS)
                .subscribe(new Subscriber<Long>() {

                    private Subscription subscription;
                    private long startTime;

                    @Override
                    public void onSubscribe(Subscription s) {
                        this.subscription = s;
                        this.startTime = System.currentTimeMillis();
                        this.subscription.request(Long.MAX_VALUE);
                    }

                    @Override
                    public void onNext(Long aLong) {
                        if((System.currentTimeMillis()) - startTime > 500) {
                            subscription.cancel();
                            System.out.println("구독 해지");
                            return;
                        }
                        System.out.println("data = " + aLong);
                    }

                    @Override
                    public void onError(Throwable t) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
        Thread.sleep(2000L);
    }
}
