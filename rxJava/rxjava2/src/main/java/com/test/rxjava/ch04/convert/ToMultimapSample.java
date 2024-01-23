package com.test.rxjava.ch04.convert;

import com.test.rxjava.DebugSingleObserver;
import io.reactivex.Flowable;
import io.reactivex.Single;
import io.reactivex.internal.util.HashMapSupplier;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Stream에서 Grouping와 유사하다. 내부적으로 들여다보면 Function객체로 만들어져 있어서 거의 동일하다고 보면 될 것같다.
 * 좀 더 동적으로 설계가 가능하다.
 */
public class ToMultimapSample {
    public static void main(String[] args) throws InterruptedException {
        Single<Map<String, Collection<Long>>> single = Flowable.interval(500L, TimeUnit.MILLISECONDS)
                .take(5)
                .toMultimap(data -> {
                    if(data % 2 == 0) {
                        return "짝수";
                    } else return "홀수";
                });

        single.subscribe(new DebugSingleObserver<>());
        Thread.sleep(3_000);

        // Custom
        Single<Map<String, Collection<Long>>> customSingle = Flowable.interval(500L, TimeUnit.MILLISECONDS)
                .take(10)
                .toMultimap(
                        data -> data%2==0? "짝수" : "홀수",
                        data -> data%2,
                        HashMapSupplier.asCallable(),
                        key -> key.equals("짝수")? new HashSet<>(): new ArrayList<>()
                );

        customSingle.subscribe(new DebugSingleObserver<>());
        Thread.sleep(10_000L);
    }
}
