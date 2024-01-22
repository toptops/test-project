package com.test.rxjava.ch01;

import io.reactivex.Flowable;

import java.util.concurrent.TimeUnit;

public class EffectedSample {

    private enum State {
        ADD, MULTIPLY;
    }

    private static State calcMethod;

    public static void main(String[] args) throws Exception{
        calcMethod = State.ADD;

        Flowable<Long> flowable = Flowable.interval(300L, TimeUnit.MILLISECONDS)
                .take(7)
                .scan((sum, data) -> {
                    if(calcMethod == State.ADD) {
                        return sum + data;
                    } else return sum * data;
                });

        flowable.subscribe(data -> System.out.println("data=" + data));
        Thread.sleep(1000);
        calcMethod = State.MULTIPLY;
        Thread.sleep(1000);
    }
}
