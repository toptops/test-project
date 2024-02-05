package com.test.reactive.ch14.error;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

/**
 * 오! 이거는 잘이용하면 좋을듯?
 */
@Slf4j
public class ErrorContinueExample {
    public static void main(String[] args) {
        Flux.just(1, 2, 4, 0, 6, 12)
                .map(n -> 12/n)
                .onErrorContinue((e, n) -> log.error("error: {}, num: {}", e.getMessage(), n ))
                .subscribe(d -> log.info("# onNext: {}", d),
                        e -> log.error("# onError: {}", e));
    }
}
