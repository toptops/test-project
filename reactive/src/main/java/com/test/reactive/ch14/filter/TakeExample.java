package com.test.reactive.ch14.filter;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

import java.time.Duration;

/**
 * 랭킹??
 */
@Slf4j
public class TakeExample {
    public static void main(String[] args) throws InterruptedException {
        Flux.interval(Duration.ofSeconds(1))
                .take(3)
                .subscribe(d -> log.info("# onNext: {}", d));

        Thread.sleep(4000L);
    }
}
