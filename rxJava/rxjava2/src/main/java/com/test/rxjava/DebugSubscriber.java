package com.test.rxjava;

import io.reactivex.subscribers.DisposableSubscriber;

public class DebugSubscriber<T> extends DisposableSubscriber<T> {
    private String label;

    public DebugSubscriber() {
        super();
    }

    public DebugSubscriber(String label) {
        super();
        this.label = label;
    }

    @Override
    public void onNext(T data) {
        String threadName = Thread.currentThread().getName();
        if(label == null) {
            System.out.println(threadName + ": " + data);
        } else System.out.println(threadName + " : " + label + ": " + data);
    }

    @Override
    public void onError(Throwable t) {
        String threadName = Thread.currentThread().getName();
        if(label == null) {
            System.out.println(threadName + ": 에러: " + t);
        } else System.out.println(threadName + ": label: " + label + ": 에러: " + t);
    }

    @Override
    public void onComplete() {
        String threadName = Thread.currentThread().getName();
        if(label == null) {
            System.out.println(threadName + ": 완료");
        } else System.out.println(threadName + " : " + label + ": 완료");
    }
}
