package com.test.rxjava.ch01;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.schedulers.Schedulers;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

public class FlowableSample {

    public static void main(String[] args) throws InterruptedException {
        Flowable<String> flowable = Flowable.create(e -> {
            String[] datas = {"Hello, World", "안녕, RxJava!"};
            for(String data : datas) {
                if(e.isCancelled()) {
                    return;
                }
                e.onNext(data);
                String treadName = Thread.currentThread().getName();
                System.out.println("threadName = " + treadName);
            }
            e.onComplete();
        }, BackpressureStrategy.BUFFER);

        flowable.observeOn(Schedulers.computation())
                .subscribe(new Subscriber<String>() {
                    private Subscription subscription;
                    @Override
                    public void onSubscribe(Subscription s) {
                        this.subscription = s;
                        this.subscription.request(1L);
                    }

                    @Override
                    public void onNext(String s) {
                        String threadName = Thread.currentThread().getName();
                        System.out.println(threadName + " : " + s);
                        this.subscription.request(1L);
                    }

                    @Override
                    public void onComplete() {
                        String threadName = Thread.currentThread().getName();
                        System.out.println(threadName + ": 완료");
                    }

                    @Override
                    public void onError(Throwable t) {
                        t.printStackTrace();
                    }
                });

        Thread.sleep(1000);
    }
}
