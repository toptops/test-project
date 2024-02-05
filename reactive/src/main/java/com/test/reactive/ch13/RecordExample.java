package com.test.reactive.ch13;

import reactor.core.publisher.Flux;

public class RecordExample {
    public static Flux<String> getCapitalizedCountry(Flux<String> source) {
        return source.map(c -> c.substring(0, 1).toUpperCase() + c.substring(1));
    }
}
