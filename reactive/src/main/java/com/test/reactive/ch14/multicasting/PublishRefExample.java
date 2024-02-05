package com.test.reactive.ch14.multicasting;

import lombok.extern.slf4j.Slf4j;
import reactor.core.Disposable;
import reactor.core.publisher.Flux;

import java.time.Duration;


/**
 * 음..? 주로 무한스트림해제해서 사용된다.
 * 좀 다른점이라면 ref는 스트림 해제되서 처음으로 돌아가는데 다른것은 기존에 있는 데이터를 이어감, 콜드 스트림이라고 봐야될듯
 *
 */
@Slf4j
public class PublishRefExample {
    public static void main(String[] args) throws InterruptedException {
        Flux<Long> publisher = Flux.interval(Duration.ofMillis(500))
                .publish().autoConnect(1);
//                .publish().refCount(1);

        Disposable disposable = publisher.subscribe(d -> log.info("# subscriber 1: {}", d));

        Thread.sleep(2100L);
        disposable.dispose();

        publisher.subscribe(d -> log.info("# subscriber 2: {}", d));
        Thread.sleep(2500L);
    }
}
