package com.test.rxjava.ch03;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

public class RetrySample {
    public static void main(String[] args) {
        Flowable<Integer> flowable = Flowable.<Integer> create(e -> {
            System.out.println("Flowable 처리 시작");
            for(int i=1;i<=3;i++) {
                if(i==2) {
                    e.onError(new Exception("예외 발생"));
                }
                e.onNext(i);
            }
            e.onComplete();
            System.out.println("Flowable 처리 완료");
        }, BackpressureStrategy.BUFFER)
        .doOnSubscribe(s -> System.out.println("flowable: doOnSubscribe"))
        .retry(2);

        flowable.subscribe(new Subscriber<Integer>() {
            @Override
            public void onSubscribe(Subscription s) {
                s.request(Long.MAX_VALUE);
            }

            @Override
            public void onNext(Integer integer) {
                System.out.println("data= " + integer);
            }

            @Override
            public void onError(Throwable t) {
                System.out.println("에러=" + t);
            }

            @Override
            public void onComplete() {
                System.out.println("종료");
            }
        });
    }
}
