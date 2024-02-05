package com.test.reactive.ch14.multicasting;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.ConnectableFlux;
import reactor.core.publisher.Flux;

import java.time.Duration;

@Slf4j
public class Publish2Example {
    private static ConnectableFlux<String> publisher;
    private static int checkedAudience;

    static {
        publisher = Flux.just("Concert part1", "Concert part2", "Concert part3")
                .delayElements(Duration.ofMillis(300L))
                .publish();
    }

    public static void main(String[] args) throws InterruptedException {
        checkAudience();
        Thread.sleep(500L);
        publisher.subscribe(d -> log.info("# audience 1 is watching {}", d));
        checkedAudience++;

        Thread.sleep(500L);
        publisher.subscribe(d -> log.info("# audience 2 is watching {}", d));
        checkedAudience++;

        checkAudience();

        Thread.sleep(500L);
        publisher.subscribe(d -> log.info("# audience 3 is watching {}", d));

        Thread.sleep(1000L);
    }

    private static void checkAudience() {
        if(checkedAudience >= 2) {
            publisher.connect();
        }
    }
}
