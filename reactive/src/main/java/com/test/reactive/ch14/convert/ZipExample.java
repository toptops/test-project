package com.test.reactive.ch14.convert;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

import java.time.Duration;

@Slf4j
public class ZipExample {
    public static void main(String[] args) throws InterruptedException {
        Flux.zip(
                Flux.just(1, 2, 3).delayElements(Duration.ofMillis(300L)),
                Flux.just(4, 5, 6).delayElements(Duration.ofMillis(500L)),
                (n1, n2) -> n1*n2
                )
                .subscribe(d -> log.info("# onNext: {}", d));

        Thread.sleep(2500L);
    }
}
