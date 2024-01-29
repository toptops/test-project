package com.test.reactive.ch07;

import reactor.core.publisher.Flux;

import java.util.Arrays;

public class ColdSequenceSample {
    public static void main(String[] args) throws InterruptedException {
        Flux<String> coldFlux = Flux.fromIterable(Arrays.asList("KOREA", "JAPAN", "CHINESE"))
                .map(String::toLowerCase);

        coldFlux.subscribe(c -> System.out.println(c));
        System.out.println("--------");
        Thread.sleep(2000L);
        coldFlux.subscribe(c -> System.out.println(c));
    }
}
