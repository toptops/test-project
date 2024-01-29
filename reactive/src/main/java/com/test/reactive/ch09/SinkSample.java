package com.test.reactive.ch09;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;
import reactor.core.scheduler.Schedulers;

import java.util.stream.IntStream;

@Slf4j
public class SinkSample {
    public static void main(String[] args) throws InterruptedException {
        int tasks = 6;

        Sinks.Many<String> unicastSink = Sinks.many().unicast()
                .onBackpressureBuffer();

        Flux<String> fluxView = unicastSink.asFlux();
        IntStream.range(1, tasks)
                .forEach(n -> {
                    try {
                        new Thread(() -> {
                            unicastSink.emitNext(doTask(n), Sinks.EmitFailureHandler.FAIL_FAST);
                            log.info("# emitted: {}", n);
                        }).start();
                        Thread.sleep(100L);
                    } catch (InterruptedException e) {
                        log.error(e.getMessage());
                    }
                });

        fluxView.publishOn(Schedulers.parallel())
                .map(result -> result + " success!")
                .doOnNext(n -> log.info("# map(): {}", n))
                .publishOn(Schedulers.parallel())
                .subscribe(d -> log.info("# onNext: {}", d));

        Thread.sleep(2000L);
    }

    private static String doTask(int n) {
        return "task " + n + " result";
    }
}
