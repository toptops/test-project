package com.test.reactive.ch13;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;
import reactor.test.scheduler.VirtualTimeScheduler;

import java.time.Duration;


/**
 * 오... 이거는 배치 테스트를 만들때 사용하면 되겠다.
 * 근데 배치 모듈에도 테스트가 필요할까??
 */
class TimeBasedSampleTest {
    @Test
    public void timeBased1ByCOVID19CountTest() {
        StepVerifier.withVirtualTime(() -> TimeBasedSample.getCOVID9Count(
                Flux.interval(Duration.ofHours(1)).take(1)
        ))
        .expectSubscription()
        .then(() -> VirtualTimeScheduler.get()
                .advanceTimeBy(Duration.ofHours(1)))
        .expectNextCount(11)
        .expectComplete()
        .verify();
    }

    @Test
    public void timeBased2ByCOVID19CountTest() {
        StepVerifier.create(TimeBasedSample.getCOVID9Count(
                    Flux.interval(Duration.ofMinutes(1))).take(1)
                )
                .expectSubscription()
                .expectNextCount(11)
                .expectComplete()
                .verify(Duration.ofSeconds(3));
    }

    @Test
    public void timeBased3ByVoteCountTest() {
        StepVerifier.withVirtualTime(() -> TimeBasedSample.getVoteCount(
                    Flux.interval(Duration.ofMinutes(1))
                ))
                .expectSubscription()
                .expectNoEvent(Duration.ofMinutes(1))
                .expectNoEvent(Duration.ofMinutes(1))
                .expectNoEvent(Duration.ofMinutes(1))
                .expectNoEvent(Duration.ofMinutes(1))
                .expectNoEvent(Duration.ofMinutes(1))
                .expectNextCount(5)
                .expectComplete()
                .verify();
    }
}