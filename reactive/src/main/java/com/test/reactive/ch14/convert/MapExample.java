package com.test.reactive.ch14.convert;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

@Slf4j
public class MapExample {
    public static void main(String[] args) {
        Flux.just("1-Circle", "3-Circle", "5-Circle")
                .map(c -> c.replace("Circle", "Rectangle"))
                .subscribe(d -> log.info("# onNext: {}", d));
    }
}
