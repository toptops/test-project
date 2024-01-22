package com.test.rxjava.ch03;

import io.reactivex.Flowable;
import io.reactivex.schedulers.Schedulers;

/**
 * 흠.. 공유자원을 컨트롤한다? 이건 공유자원을 만드는 상황을 안만드는게 제일 베스트인거 같은데...?
 */
public class CounterSample {

    public static void main(String[] args) throws InterruptedException {
        final Counter counter = new Counter();
        Flowable.range(1, 10000)
                .subscribeOn(Schedulers.computation())
                .observeOn(Schedulers.computation())
                .subscribe(
                        data -> counter.increment(),
                        error -> System.out.println("에러: " + error),
                        () -> System.out.println("counter.get(): " + counter.get())
                );

        Flowable.range(1, 10000)
                .subscribeOn(Schedulers.computation())
                .observeOn(Schedulers.computation())
                .subscribe(
                        data -> counter.increment(),
                        error -> System.out.println("에러: " + error),
                        () -> System.out.println("counter.get(): " + counter.get())
                );
        Thread.sleep(1000L);
    }

    static class Counter {
        private volatile int count;

        void increment() {
            count++;
        }

        int get() {
            return count;
        }
    }
}
