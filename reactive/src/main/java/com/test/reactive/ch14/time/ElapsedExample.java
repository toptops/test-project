package com.test.reactive.ch14.time;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

import java.time.Duration;

@Slf4j
public class ElapsedExample {

    public static void main(String[] args) throws InterruptedException {
        Flux.range(1, 5)
                .delayElements(Duration.ofSeconds(1))
                .elapsed()
                .subscribe(d -> log.info("# onNext: {}, time: {}", d.getT2(), d.getT1()));

        Thread.sleep(6000);
    }
}
