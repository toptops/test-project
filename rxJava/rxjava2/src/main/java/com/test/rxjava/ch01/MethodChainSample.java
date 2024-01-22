package com.test.rxjava.ch01;

import io.reactivex.Flowable;

import java.util.ArrayList;
import java.util.List;

public class MethodChainSample {
    public static void main(String[] args) {
        List<Integer> list = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        Flowable<Integer> flowable = Flowable.just(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
                .filter(d -> d%2==0)
                .map(d -> d*100);

        flowable.subscribe(d -> System.out.println("d = " + d));
    }
}
