package com.test.reactive.ch13;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.Base64;

class ContextTestExampleTest {

    @Test
    public void getSecretMessageTest() {
        Mono<String> source = Mono.just("hello");

        StepVerifier.create(ContextExample
                .getSecretMessage(source)
                .contextWrite(c -> c.put("secretMessage", "Hello, Reactor"))
                .contextWrite(c -> c.put("secretKey", "aGVsbG8=")))
                .expectSubscription()
                .expectAccessibleContext()
                .hasKey("secretKey")
                .hasKey("secretMessage")
                .then()
                .expectNext("Hello, Reactor")
                .expectComplete()
                .verify();
    }
}