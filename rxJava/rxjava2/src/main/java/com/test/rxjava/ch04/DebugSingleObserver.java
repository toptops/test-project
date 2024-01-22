package com.test.rxjava.ch04;

import io.reactivex.annotations.NonNull;
import io.reactivex.observers.DisposableSingleObserver;

public class DebugSingleObserver<T> extends DisposableSingleObserver<T> {
    private String label;

    public DebugSingleObserver() {
        super();
    }

    public DebugSingleObserver(String label) {
        super();
        this.label = label;
    }

    @Override
    public void onSuccess(@NonNull T data) {
        String threadName = Thread.currentThread().getName();
        if(label == null) {
            System.out.println(threadName + ": " + data);
        } else System.out.println(threadName + " : " + label + ": " + data);
    }

    @Override
    public void onError(@NonNull Throwable t) {
        String threadName = Thread.currentThread().getName();
        if(label == null) {
            System.out.println(threadName + ": 에러: " + t);
        } else System.out.println(threadName + ": label: " + label + ": 에러: " + t);
    }
}
