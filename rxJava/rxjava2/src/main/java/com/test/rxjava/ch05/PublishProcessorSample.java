package com.test.rxjava.ch05;

import com.test.rxjava.DebugSubscriber;
import io.reactivex.processors.PublishProcessor;

public class PublishProcessorSample {
    public static void main(String[] args) {
        PublishProcessor<Integer> processor = PublishProcessor.create();
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
