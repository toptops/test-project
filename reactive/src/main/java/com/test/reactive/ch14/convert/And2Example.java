package com.test.reactive.ch14.convert;

import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Publisher;
import reactor.core.publisher.Mono;

import java.time.Duration;

@Slf4j
public class And2Example {
    public static void main(String[] args) throws InterruptedException {
        restartApplicationServer()
                .and(restartDBServer())
                .subscribe(
                        d -> log.info("# onNext: {}", d),
                        e -> log.error("# onError: {}", e),
                        () -> log.info("# onComplete")
                );
        Thread.sleep(6000);
    }

    private static Publisher<String> restartDBServer() {
        return Mono.just("DB Server was restarted successfully.")
                .delayElement(Duration.ofSeconds(4))
                .doOnNext(log::info);
    }

    private static Mono<String> restartApplicationServer() {
        return Mono.just("Application Server was restarted successfully.")
                .delayElement(Duration.ofSeconds(2))
                .doOnNext(log::info);
    }
}
