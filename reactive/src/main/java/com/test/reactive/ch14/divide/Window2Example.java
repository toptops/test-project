package com.test.reactive.ch14.divide;

import com.test.reactive.SampleData;
import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Subscription;
import reactor.core.publisher.BaseSubscriber;
import reactor.core.publisher.Flux;
import reactor.math.MathFlux;


@Slf4j
public class Window2Example {

    public static void main(String[] args) {
        Flux.fromIterable(SampleData.monthlyBookSales2021)
                .window(3)
                .flatMap(f -> MathFlux.sumInt(f))
                .subscribe(new BaseSubscriber<>() {
                    @Override
                    protected void hookOnSubscribe(Subscription subscription) {
                        subscription.request(2);
                    }

                    @Override
                    protected void hookOnNext(Integer value) {
                        log.info("# onNext: {}", value);
                        request(2);
                    }
                });
    }
}
