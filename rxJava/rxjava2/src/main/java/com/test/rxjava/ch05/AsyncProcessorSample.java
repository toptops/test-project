package com.test.rxjava.ch05;

import com.test.rxjava.DebugSubscriber;
import io.reactivex.processors.AsyncProcessor;

/**
 * 이건 뭐지? 마지막 결과값만 받아서 전해주려는 건가?
 */
public class AsyncProcessorSample {
    public static void main(String[] args) {
        AsyncProcessor processor = AsyncProcessor.create();

        processor.subscribe(new DebugSubscriber<>("No.1"));

        processor.onNext(1);
        processor.onNext(2);
        processor.onNext(3);

        processor.subscribe(new DebugSubscriber<>("No.2"));

        processor.onNext(4);
        processor.onNext(5);
        processor.onComplete();

        processor.subscribe(new DebugSubscriber<>("No.3"));
    }
}
