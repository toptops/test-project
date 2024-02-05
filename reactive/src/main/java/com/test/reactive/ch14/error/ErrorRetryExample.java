package com.test.reactive.ch14.error;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

import java.time.Duration;

@Slf4j
public class ErrorRetryExample {
    public static void main(String[] args) throws InterruptedException {
        final int[] count = {1};
        Flux.range(1, 3)
                .delayElements(Duration.ofSeconds(1))
                .map(n -> {
                    try {
                        if(n == 3 && count[0] == 1) {
                            count[0]++;
                            Thread.sleep(1000);
                        }
                    } catch (InterruptedException e) {}
                    return n;
                })
                .timeout(Duration.ofMillis(1500))
                .retry(1)
                .subscribe(d -> log.info("# onNext: {}", d),
                        e -> log.error("# onError: ", e),
                        () -> log.info("# onComplete"));
        Thread.sleep(7000);
    }
}
