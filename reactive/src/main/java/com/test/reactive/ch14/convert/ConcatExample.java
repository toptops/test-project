package com.test.reactive.ch14.convert;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

@Slf4j
public class ConcatExample {
    public static void main(String[] args) {
        Flux.concat(Flux.just(1, 2, 3), Flux.just(4, 5))
                .subscribe(d -> log.info("# onNext: {}", d));
    }
}
