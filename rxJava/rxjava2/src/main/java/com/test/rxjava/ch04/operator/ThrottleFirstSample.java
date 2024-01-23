package com.test.rxjava.ch04.operator;

import com.test.rxjava.DebugSubscriber;
import io.reactivex.Flowable;

import java.util.concurrent.TimeUnit;

/**
 * 음?? 이건 어디다 써먹냐.. 단기간에 대량으로 들어오는 데이터 중 모두 필요한게 아니라면?
 * 길안내나 네이비게이션에서 쓸만하려나?
 */
public class ThrottleFirstSample {

    public static void main(String[] args) throws InterruptedException {
        Flowable<Long> flowable = Flowable.interval(300L, TimeUnit.MILLISECONDS)
                .take(10)
                .throttleFirst(1000L, TimeUnit.MILLISECONDS);
        flowable.subscribe(new DebugSubscriber<>());
        Thread.sleep(4000L);
    }
}
