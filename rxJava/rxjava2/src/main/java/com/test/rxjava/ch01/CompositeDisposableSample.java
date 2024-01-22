package com.test.rxjava.ch01;

import io.reactivex.Flowable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class CompositeDisposableSample {
    public static void main(String[] args) throws InterruptedException {
        CompositeDisposable compositeDisposable = new CompositeDisposable();
        Disposable flowable1 = Flowable.range(1, 3)
                .doOnCancel(() -> System.out.println("No.1 Cancel"))
                .observeOn(Schedulers.computation())
                .subscribe(data -> {
                    Thread.sleep(100);
                    System.out.println("No.1: " + data);
                });

        Disposable flowable2 = Flowable.range(1, 3)
                .doOnCancel(() -> System.out.println("No.2 Cancel"))
                .observeOn(Schedulers.computation())
                .subscribe(data -> {
                    Thread.sleep(100);
                    System.out.println("No.2: " + data);
                });

        compositeDisposable.add(flowable1);
        compositeDisposable.add(flowable2);
        Thread.sleep(150L);
        compositeDisposable.dispose();
    }
}
