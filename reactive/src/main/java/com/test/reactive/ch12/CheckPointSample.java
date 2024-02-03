package com.test.reactive.ch12;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

@Slf4j
public class CheckPointSample {
    public static void main(String[] args) {
        Flux.just(2, 4, 6, 8)
            .zipWith(Flux.just(1, 2, 3, 0), (x, y) -> x/y)
            .checkpoint()
            .map(n -> n*2)
            .checkpoint()
            .subscribe(
                    d -> log.info("# onNext: {}", d),
                    e -> log.error("# onError: {}", e)
            );
    }
}
