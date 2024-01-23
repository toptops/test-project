package com.test.rxjava;

import io.reactivex.annotations.NonNull;
import io.reactivex.observers.DisposableMaybeObserver;

public class DebugMaybeObserver<T> extends DisposableMaybeObserver<T> {
    private String label;

    public DebugMaybeObserver() {
        super();
    }

    public DebugMaybeObserver(String label) {
        super();
        this.label = label;
    }

    @Override
    public void onSuccess(T data) {
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
