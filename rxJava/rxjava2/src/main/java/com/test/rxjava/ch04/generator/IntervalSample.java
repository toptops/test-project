package com.test.rxjava.ch04.generator;

import io.reactivex.Flowable;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

public class IntervalSample {
    public static void main(String[] args) throws InterruptedException {
        final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("mm:ss.SSS");

        Flowable<Long> flowable = Flowable.interval(1000L, TimeUnit.MILLISECONDS);
        System.out.println("시작시간 : " + LocalTime.now().format(formatter));

        flowable.subscribe(data -> {
            String threadName = Thread.currentThread().getName();
            String time = LocalTime.now().format(formatter);
            System.out.println(threadName + " : " + time + " : " + data);
        });

        Thread.sleep(5000);
    }
}
