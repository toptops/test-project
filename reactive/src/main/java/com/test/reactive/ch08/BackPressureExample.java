package com.test.reactive.ch08;

import lombok.SneakyThrows;
import org.reactivestreams.Subscription;
import reactor.core.publisher.BaseSubscriber;
import reactor.core.publisher.Flux;

/**
 * rxjava랑 별차이가 없다..
 */
public class BackPressureExample {
    public static void main(String[] args) {
        Flux.range(1, 5)
                .doOnRequest(d -> System.out.println("data: " + d))
                .subscribe(new BaseSubscriber<>() {
                    @Override
                    protected void hookOnSubscribe(Subscription subscription) {
                        request(1);
                    }

                    @Override
                    @SneakyThrows
                    protected void hookOnNext(Integer value) {
                        Thread.sleep(1000);
                        System.out.println("hookOnNext: " + value);
                        request(1);
                    }
                });
    }
}
