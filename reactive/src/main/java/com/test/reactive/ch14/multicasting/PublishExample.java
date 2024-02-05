package com.test.reactive.ch14.multicasting;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.ConnectableFlux;
import reactor.core.publisher.Flux;

import java.time.Duration;

@Slf4j
public class PublishExample {

    public static void main(String[] args) throws InterruptedException {
        ConnectableFlux<Integer> flux = Flux.range(1, 5)
                .delayElements(Duration.ofMillis(300L))
                .publish();

        Thread.sleep(500L);
        flux.subscribe(d -> log.info("# subscribe1: {}", d));

        Thread.sleep(200L);
        flux.subscribe(d -> log.info("# subscribe2: {}", d));

        flux.connect();

        Thread.sleep(1000L);
        flux.subscribe(d -> log.info("# subscribe3: {}", d));

        Thread.sleep(2000L);
    }
}
