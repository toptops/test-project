package com.test.reactive.ch14.convert;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

@Slf4j
public class AndExample {

    /**
     * 로딩이나 데이터 처리에만 쓰이는건가?? 실제 결과 값은 필요 없는 것..?
     *
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
        Mono.just("Task 1")
                .delayElement(Duration.ofSeconds(1))
                .doOnNext(d -> log.info("# Mono doOnNext: {}", d))
                .and(
                        Flux.just("Task 2", "Task 3")
                                .delayElements(Duration.ofMillis(600))
                                .doOnNext(d -> log.info("# FluxdoOnNext: {}", d))
                )
                .subscribe(
                        d -> log.info("# onNext: {}", d),
                        e -> log.error("# onError: {}", e),
                        () -> log.info("# onComplete")
                );
        Thread.sleep(5000);
    }
}
