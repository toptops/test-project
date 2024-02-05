package com.test.reactive.ch13;

import org.junit.jupiter.api.Test;
import reactor.test.StepVerifier;

class BackpressureExampleTest {

    @Test
    public void backpressureByGeneratorTest() {
//        StepVerifier.create(BackpressureExample.generateNumber(), 1L)
//                .thenConsumeWhile(n -> n >= 1)
//                .verifyComplete();

        StepVerifier.create(BackpressureExample.generateNumber(), 1L)
                .thenConsumeWhile(n -> n >= 1)
                .expectError()
                .verifyThenAssertThat()
                .hasDroppedElements();
    }
}