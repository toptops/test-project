package com.test.reactive.ch14.convert;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

import java.time.Duration;

@Slf4j
public class MergeExample {
    public static void main(String[] args) throws InterruptedException {
        Flux.merge(
                    Flux.just(1, 2, 3, 4).delayElements(Duration.ofMillis(300L)),
                    Flux.just(5, 6, 7).delayElements(Duration.ofMillis(500L))
                )
                .subscribe(d -> log.info("# onNext: {}", d));
        Thread.sleep(2000L);
    }
}
