package com.test.rxjava.ch04.convert;

import com.test.rxjava.DebugSubscriber;
import io.reactivex.Flowable;

/**
 * flowable 내부에서 결과를 만들어 리턴할때 왜 flowable로 하는걸까? map과 동일하게 해야되는거 아닌가?
 * flowable map의 줄임말인가 보다.
 */
public class FlatMapSample1 {
    public static void main(String[] args) {
        Flowable<String> flowable = Flowable.just("A", "", "B", "", "C")
                .flatMap(data -> {
                    if("".equals(data)) {
                        return Flowable.empty();
                    } else return Flowable.just(data.toLowerCase());
                });

        flowable.subscribe(new DebugSubscriber<>());
    }
}
