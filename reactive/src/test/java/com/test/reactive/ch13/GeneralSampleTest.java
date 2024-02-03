package com.test.reactive.ch13;

import com.test.reactive.ch13.GeneralSample;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;
import reactor.test.StepVerifierOptions;

public class GeneralSampleTest {
    @Test
    public void generalTest() {
        StepVerifier.create(GeneralSample.sayHello())
                .expectSubscription()
                .as("# expect subscription")
                .expectNext("Hi")
                .as("# expect Hi")
                .expectNext("Reactor")
                .as("# expect Reactor")
                .verifyComplete();
    }

    @Test
    public void generalTest2ByDivide() {
        Flux<Integer> source = Flux.just(2, 4, 6, 8, 10);
        StepVerifier.create(GeneralSample.divideByTwo(source))
                .expectSubscription()
                .expectNext(1)
                .expectNext(2)
                .expectNext(3)
                .expectNext(4)
//                .expectNext(1, 2, 3, 4)
                .expectError()
                .verify();

    }

    @Test
    public void generalTest3ByTakeNumber() {
        Flux<Integer> source = Flux.range(0, 1000);
        StepVerifier.create(GeneralSample.takeNumber(source, 500),
                StepVerifierOptions.create().scenarioName("Verify from 0 to 499"))
                .expectSubscription()
                .expectNext(0)
                .expectNext(498)
                .expectNext(500)
                .expectComplete()
                .verify();
    }
}
