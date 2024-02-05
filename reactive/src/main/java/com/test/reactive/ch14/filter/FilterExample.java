package com.test.reactive.ch14.filter;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

@Slf4j
public class FilterExample {
    public static void main(String[] args) {
        Flux.range(1, 20)
                .filter(n -> n%2 != 0)
                .subscribe(d -> log.info("# onNext: {}", d));
    }
}
