package com.test.reactive.ch09;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

import static reactor.core.publisher.Sinks.EmitFailureHandler.FAIL_FAST;

@Slf4j
public class SinkManyUnicastSample {
    public static void main(String[] args) {
        Sinks.Many<Integer> unicastSink = Sinks.many().unicast().onBackpressureBuffer();
        Flux<Integer> flux = unicastSink.asFlux();

        unicastSink.emitNext(1, FAIL_FAST);
        unicastSink.emitNext(2, FAIL_FAST);

        flux.subscribe(d -> log.info("# Subscriber1: {}",  d));

        unicastSink.emitNext(3, FAIL_FAST);

//        flux.subscribe(d -> log.info("# Subscriber2: {}",  d));
    }
}
