package com.test.reactive.ch14.generate;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Slf4j
public class JustOrEmptyExample {
    public static void main(String[] args) {
        Mono.justOrEmpty(null)
                .subscribe(d -> {}, e -> {}, () -> log.info("# onComplete"));
    }
}
