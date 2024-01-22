package com.test.rxjava.ch01;

import io.reactivex.Flowable;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

public class RequestMethodSample {
    public static void main(String[] args) {
        Flowable.just(1, 2, 3)
                .subscribe(new Subscriber<Integer>() {
                               @Override
                               public void onSubscribe(Subscription s) {
                                   System.out.println("시작한다");
                                   s.request(Long.MAX_VALUE);
                                   System.out.println("종료한다");
                               }

                               @Override
                               public void onNext(Integer integer) {
                                   System.out.println(integer);
                               }

                               @Override
                               public void onError(Throwable t) {
                                   System.err.println("에러=" + t);
                               }

                               @Override
                               public void onComplete() {
                                   System.out.println("완료!");
                               }
                           }
                );
    }
}
