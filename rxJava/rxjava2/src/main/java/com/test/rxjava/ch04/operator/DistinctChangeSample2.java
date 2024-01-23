package com.test.rxjava.ch04.operator;

import com.test.rxjava.DebugSubscriber;
import io.reactivex.Flowable;

import java.math.BigDecimal;

public class DistinctChangeSample2 {
    public static void main(String[] args) {
        Flowable<String> flowable = Flowable.just("1", "1.0", "0.1", "0.10", "1")
                .distinctUntilChanged((d1, d2) -> {
                    BigDecimal b1 = new BigDecimal(d1);
                    BigDecimal b2 = new BigDecimal(d2);
                    return b1.compareTo(b2) == 0;
                });

        flowable.subscribe(new DebugSubscriber<>());
    }
}
