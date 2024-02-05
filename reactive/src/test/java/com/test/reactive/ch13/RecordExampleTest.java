package com.test.reactive.ch13;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.*;

class RecordExampleTest {

    @Test
    public void recordByCityTest() {
        StepVerifier.create(RecordExample.getCapitalizedCountry(
                Flux.just("korea", "england", "canada", "india")))
                .expectSubscription()
                .recordWith(ArrayList::new)
                .thenConsumeWhile(c -> !c.isEmpty())
                .consumeRecordedWith(c -> assertThat(
                        c.stream().allMatch(c1 -> Character.isUpperCase(c1.charAt(0)))
                ).isTrue())
                .expectComplete()
                .verify();
    }

    @Test
    public void recordByCityTest2() {
        StepVerifier.create(RecordExample.getCapitalizedCountry(
                        Flux.just("korea", "england", "canada", "india")))
                .expectSubscription()
                .recordWith(ArrayList::new)
                .thenConsumeWhile(c -> !c.isEmpty())
                .expectRecordedMatches(c -> c.stream().allMatch(c1 -> Character.isUpperCase(c1.charAt(0))))
                .expectComplete()
                .verify();
    }
}