package com.test.reactive.ch14.error;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
public class ErrorExample {
    public static void main(String[] args) {
        Flux.range(1, 5)
                .flatMap(n -> {
                    if((n*2)%3 == 0) {
                        return Flux.error(new IllegalArgumentException("Not allowed multiple of 3"));
                    } else {
                        return Mono.just(n*2);
                    }
                })
                .subscribe(d -> log.info("# onNext: {}", d),
                        e -> log.error("# onError: {}", e));
    }
}
