package com.test.reactive.ch14.generate;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@Slf4j
public class DeferExample {
    /**
     * defer는 즉시 데이터를 가져오는게 아니라 호출 시점에 가져온다.
     * 음... 실시간으로 보면 되겠다
     *
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
        Mono<LocalDateTime> justMono = Mono.just(LocalDateTime.now());
        Mono<LocalDateTime> deferMono = Mono.defer(() -> Mono.just(LocalDateTime.now()));

        Thread.sleep(2000L);

        justMono.subscribe(d -> log.info("# onNext just1: {}", d));
        deferMono.subscribe(d -> log.info("# onNext defer1: {}", d));

        Thread.sleep(2000L);

        justMono.subscribe(d -> log.info("# onNext just2: {}", d));
        deferMono.subscribe(d -> log.info("# onNext defer2: {}", d));
    }
}
