package com.test.reactive.ch13;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

public class StepVerifierTest {

    @Test
    public void stepVerifierTest() {
        StepVerifier.create(Mono.just("Hello Reactor"))
                .expectNext("Hello Reactor")
                .expectComplete()
                .verify();
    }
}
