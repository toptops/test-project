package com.test.rxjava.ch02;

import io.reactivex.Flowable;

import java.util.Arrays;
import java.util.List;

public class FlowableIteratorSample {
    public static void main(String[] args) {
        List<String> list = Arrays.asList("A", "B", "C");
        Flowable<String> flowable = Flowable.fromIterable(list);
        flowable.subscribe(d -> System.out.println("data = " + d));
    }
}
