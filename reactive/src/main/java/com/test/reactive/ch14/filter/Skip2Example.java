package com.test.reactive.ch14.filter;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

import java.time.Duration;

@Slf4j
public class Skip2Example {

    public static void main(String[] args) throws InterruptedException {
        Flux.interval(Duration.ofMillis(300))
                .skip(Duration.ofSeconds(1))
                .subscribe(d -> log.info("# onNext: {}", d));
        Thread.sleep(2000L);
    }
}
