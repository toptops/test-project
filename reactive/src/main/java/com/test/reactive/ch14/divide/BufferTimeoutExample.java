package com.test.reactive.ch14.divide;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

import java.time.Duration;

@Slf4j
public class BufferTimeoutExample {

    public static void main(String[] args) {
        Flux.range(1, 20)
                .map(n -> {
                    try {
                        if(n < 10) {
                            Thread.sleep(100L);
                        } else {
                            Thread.sleep(300L);
                        }
                    } catch (InterruptedException e) {}
                    return n;
                })
                .bufferTimeout(3, Duration.ofMillis(400L))
                .subscribe(b -> log.info("# onNext: {}", b));
    }
}
