package com.test.rxjava.ch05;

import com.test.rxjava.DebugSubscriber;
import io.reactivex.processors.UnicastProcessor;

public class UnicastProcessorSample {
    public static void main(String[] args) {
        UnicastProcessor processor = UnicastProcessor.create();

        processor.subscribe(new DebugSubscriber<>("No.1"));

        processor.onNext(1);
        processor.onNext(2);
        processor.onNext(3);

        processor.subscribe(new DebugSubscriber<>("No.2"));
        processor.onNext(4);
        processor.onNext(5);

        processor.onComplete();
    }
}
