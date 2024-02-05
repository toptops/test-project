package com.test.reactive.ch14.generate;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

@Slf4j
public class RangeExample {
    public static void main(String[] args) {
        Flux.range(5, 10)
                .subscribe(d -> log.info("{}", d));
    }
}
