package com.test.rxjava.ch04.util;

import com.test.rxjava.DebugSubscriber;
import io.reactivex.Flowable;

import java.util.concurrent.TimeUnit;

/**
 * for문을 비동기처리 하는 방법인가...? 이게 더 가독성이 좋네
 */
public class RepeatWhenSample {
    public static void main(String[] args) throws InterruptedException {
        Flowable<String> flowable = Flowable.just(1, 2, 3)
                .repeatWhen(c -> c.delay(1000, TimeUnit.MILLISECONDS)
                        .take(2)
                        .doOnNext(d -> System.out.println("data: " + d))
                        .doOnComplete(() -> System.out.println("완료!"))
                )
                .map(d -> {
                    long time = System.currentTimeMillis();
                    return time + "ms: " + d;
                });
        flowable.subscribe(new DebugSubscriber<>());
        Thread.sleep(5000L);
    }
}
