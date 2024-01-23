package com.test.rxjava.ch04.convert;

import com.test.rxjava.DebugSingleObserver;
import io.reactivex.Flowable;
import io.reactivex.Single;

import java.util.Map;

public class ToMapSample2 {
    public static void main(String[] args) {
        Single<Map<Long, String>> single = Flowable.just("1A", "2B", "3C", "1D", "2E")
                .toMap(
                        data -> Long.valueOf(data.substring(0, 1)),
                        data -> data.substring(1)
                );
        single.subscribe(new DebugSingleObserver<>());
    }
}
