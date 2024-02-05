package com.test.reactive.ch14.convert;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

@Slf4j
public class FlatMapExample {
    public static void main(String[] args) {
        Flux.just("Good", "Bad")
                .flatMap(f -> Flux.just("Morning", "Afternoon", "Evening")
                        .map(t -> f + " " + t))
                .subscribe(log::info);
    }
}
