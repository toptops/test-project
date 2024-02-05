package com.test.reactive.ch13;

import reactor.core.publisher.Mono;

import java.util.Base64;

public class ContextExample {

    public static Mono<String> getSecretMessage(Mono<String> keySource) {
        return keySource.zipWith(Mono.deferContextual(c -> Mono.just((String)c.get("secretKey"))))
                .filter(tp -> tp.getT1().equals(new String(Base64.getDecoder().decode(tp.getT2()))))
                .transformDeferredContextual((mono, c) -> mono.map(notUse -> c.get("secretMessage")));
    }
}
