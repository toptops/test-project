package com.test.reactive.ch14.filter;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

import java.time.Duration;

@Slf4j
public class SkipExample {
    public static void main(String[] args) throws InterruptedException {
        Flux.interval(Duration.ofSeconds(1))
                .skip(2)
                .subscribe(d -> log.info("# onNext: {}", d));

        Thread.sleep(5000L);
    }
}
