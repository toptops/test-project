package com.test.reactive.ch14.generate;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;
import reactor.core.scheduler.Schedulers;

@Slf4j
public class Create3Example {
    static int start = 1;
    static int end = 4;

    public static void main(String[] args) throws InterruptedException {
        Flux.create((FluxSink<Integer> sink) -> {
                    sink.onRequest(n -> {
                        log.info("# requested: {}", n);
                        try {
                            Thread.sleep(500L);
                            for (int i = start; i <= end; i++) {
                                sink.next(i);
                            }
                            start += 4;
                            end += 4;
                        } catch (InterruptedException e) {
                        }
                    });

                    sink.onDispose(() -> log.info("# clean up"));
                }, FluxSink.OverflowStrategy.DROP)
                .subscribeOn(Schedulers.boundedElastic())
                .publishOn(Schedulers.parallel(), 2)
                .subscribe(d -> log.info("# onNext: {}", d));

        Thread.sleep(3000);
    }
}
