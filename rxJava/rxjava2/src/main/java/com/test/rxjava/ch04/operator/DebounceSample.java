package com.test.rxjava.ch04.operator;

import com.test.rxjava.DebugSubscriber;
import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;

import java.util.concurrent.TimeUnit;

/**
 * 앞썬 ThrottleWithTimeoutSample.class랑 결과는 같지만 조건을 다양하게 줄수있다.
 */
public class DebounceSample {
    public static void main(String[] args) {
        Flowable<String> flowable = Flowable.<String>create(
                e -> {
                    e.onNext("A");
                    Thread.sleep(1000L);

                    e.onNext("B");
                    Thread.sleep(300L);

                    e.onNext("C");
                    Thread.sleep(300L);

                    e.onNext("D");
                    Thread.sleep(1000L);

                    // 가장 마지막껀 그래도 출력이 되네
                    e.onNext("E");
                    Thread.sleep(100L);

                    e.onComplete();
                }, BackpressureStrategy.BUFFER
        ).debounce(data -> {
            // 어떻게 사용하는지 좀더 자세히 테스트 해봐야겠다..
            System.out.println(data);
            return Flowable.timer(500L, TimeUnit.MILLISECONDS);
        });
        flowable.subscribe(new DebugSubscriber<>());
    }
}
