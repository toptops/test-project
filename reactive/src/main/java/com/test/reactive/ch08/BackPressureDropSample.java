package com.test.reactive.ch08;

import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.time.Duration;

public class BackPressureDropSample {
    public static void main(String[] args) throws InterruptedException {
        Flux
            .interval(Duration.ofMillis(1L))
            .onBackpressureDrop(d -> System.out.println("Drop: " + d))
            .publishOn(Schedulers.parallel())
            .subscribe(d -> {
                try {
                    Thread.sleep(5L);
                } catch (Exception e) {}
                System.out.println("next: " + d);
            },
            error -> System.out.println(error));
        Thread.sleep(2000L);
    }
}
