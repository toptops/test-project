package com.test.rxjava.ch04.convert;

import com.test.rxjava.ch04.DebugSubscriber;
import io.reactivex.Flowable;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 오? 생산시점과 출하시점이 다르다. 즉, 데이터를 생산하고 buffer쌓으며 데이터를 구독자에게 넘겨줄때 조건이나 시간에 따라 커스텀하게 넘겨줄수 있다.
 */
public class BufferSample2 {
    public static void main(String[] args) throws InterruptedException {
        Flowable<List<Long>> flowable = Flowable.interval(300L, TimeUnit.MILLISECONDS)
                .take(7)
                // 시간에 따라 버퍼에 있는 데이터를 뿌려준다.
                // 0.3초마다 데이터를 쌓는데 1초마다 버퍼에 있는 데이터를 통지함으로 3개식 넘어간다.
                .buffer(() -> Flowable.timer(1000L, TimeUnit.MILLISECONDS));

        flowable.subscribe(new DebugSubscriber<>());
        Thread.sleep(4000L);
    }
}
