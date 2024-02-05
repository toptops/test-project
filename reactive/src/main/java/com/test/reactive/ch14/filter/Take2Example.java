package com.test.reactive.ch14.filter;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

import java.time.Duration;

/**
 * 오!? 시간초 지정된것도 된다.
 */
@Slf4j
public class Take2Example {

    public static void main(String[] args) throws InterruptedException {
        Flux.interval(Duration.ofSeconds(1))
                .take(Duration.ofMillis(2500))
                .subscribe(d -> log.info("# onNext: {}", d));

        Thread.sleep(3000L);
    }
}
