package com.test.reactive.ch14.multicasting;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

import java.time.Duration;

@Slf4j
public class PublishAutoConnectExample {

    public static void main(String[] args) throws InterruptedException {
        Flux<String> publisher = Flux.just("Concert part1", "Concert part2", "Concert part3")
                .delayElements(Duration.ofMillis(300L))
                .publish()
                .autoConnect(2);

        Thread.sleep(500L);
        publisher.subscribe(d -> log.info("# audience 1 is watching {}", d));

        Thread.sleep(500L);
        publisher.subscribe(d -> log.info("# audience 2 is watching {}", d));

        Thread.sleep(500L);
        publisher.subscribe(d -> log.info("# audience 3 is watching {}", d));
        publisher.subscribe(d -> log.info("# audience 4 is watching {}", d));

        Thread.sleep(10000L);
    }
}
