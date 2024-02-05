package com.test.reactive.ch14.divide;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

@Slf4j
public class BufferExample {

    public static void main(String[] args) {
        Flux.range(1, 95)
                .buffer(10)
                .subscribe(b -> log.info("# onNext: {}", b));
    }
}
