package com.test.rxjava.ch04.convert;

import com.test.rxjava.DebugSingleObserver;
import io.reactivex.Flowable;
import io.reactivex.Single;

import java.util.Map;

public class ToMapSample1 {
    public static void main(String[] args) {
        // 중복이 있으면 덮어버리는군..?
        Single<Map<Long, String>> single = Flowable.just("1A", "2B", "3C", "1D", "2E")
                .toMap(data -> Long.valueOf(data.substring(0, 1)));

        single.subscribe(new DebugSingleObserver<>());
    }
}
