package com.test.reactive.ch07;

import reactor.core.publisher.Flux;

import java.time.Duration;

public class HotSequenceSample {
    public static void main(String[] args) throws InterruptedException {
        String[] singers = {"Singer A", "Singer B", "Singer C", "Singer D", "Singer E"};

        Flux<String> concertFlux = Flux.fromArray(singers)
                .delayElements(Duration.ofSeconds(1))
                .share();

        concertFlux.subscribe(s -> System.out.println("Subscribe1: " + s));

        Thread.sleep(2500);

        concertFlux.subscribe(s -> System.out.println("Subscribe2: " + s));
        Thread.sleep(3000);
    }
}
