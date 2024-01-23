package com.test.rxjava.ch04.convert;

import com.test.rxjava.DebugSingleObserver;
import io.reactivex.Flowable;
import io.reactivex.Single;

import java.util.List;

/**
 * 오! 좀 쓸만한게 나왔다. 결과를 비동기로 생성하고 결과를 리스트로 받는다. 괜찬은데?
 */
public class ToListSample {
    public static void main(String[] args) {
        Single<List<String>> single = Flowable.just("A", "B", "C", "D", "E")
                .map(data -> data.toLowerCase())
                .toList();

        single.subscribe(new DebugSingleObserver<>());
    }
}
