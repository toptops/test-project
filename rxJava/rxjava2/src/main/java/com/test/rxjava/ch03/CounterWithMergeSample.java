package com.test.rxjava.ch03;

import io.reactivex.Flowable;
import io.reactivex.schedulers.Schedulers;

public class CounterWithMergeSample {
    public static void main(String[] args) throws InterruptedException {
        final Counter counter = new Counter();
        Flowable<Integer> source1 = Flowable.range(1, 10000)
                .subscribeOn(Schedulers.computation())
                .observeOn(Schedulers.computation());

        Flowable<Integer> source2 = Flowable.range(1, 10000)
                .subscribeOn(Schedulers.computation())
                .observeOn(Schedulers.computation());

        Flowable.merge(source1, source2)
                .subscribe(
                        data -> counter.increment(),
                        error -> System.out.println("에러: " + error),
                        () -> System.out.println("counter.get(): " + counter.get())
                );
        Thread.sleep(1000);
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
