package com.test.reactive.ch13;

import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;

public class BackpressureExample {
    public static Flux<Integer> generateNumber() {
        return Flux.create(e -> {
            for(int i=1; i<= 100; i++) {
                e.next(i);
            }
            e.complete();
        }, FluxSink.OverflowStrategy.ERROR);
    }
}
