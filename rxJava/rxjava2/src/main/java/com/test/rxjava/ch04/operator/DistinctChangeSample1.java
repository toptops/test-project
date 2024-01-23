package com.test.rxjava.ch04.operator;

import com.test.rxjava.DebugSubscriber;
import io.reactivex.Flowable;

/**
 * 아..? 연속된 데이터는 통지하지 않는다. a, a <- 단 A, a, A, a 형식의 중복은 된다.
 */
public class DistinctChangeSample1 {
    public static void main(String[] args) {
        Flowable<String> flowable = Flowable.just("A", "a", "a", "A", "a")
                .distinctUntilChanged();

        flowable.subscribe(new DebugSubscriber<>());
    }
}
