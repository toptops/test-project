package com.test.reactive.ch09;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

import static reactor.core.publisher.Sinks.EmitFailureHandler.FAIL_FAST;

@Slf4j
public class SinkManyMulticastSample {
    public static void main(String[] args) {

        Sinks.Many<Integer> multicastSink = Sinks.many().multicast().onBackpressureBuffer();
        Flux<Integer> flux = multicastSink.asFlux();

        multicastSink.emitNext(1, FAIL_FAST);
        multicastSink.emitNext(2, FAIL_FAST);

        flux.subscribe(d -> log.info("# Subscriber1: {}",  d));
        flux.subscribe(d -> log.info("# Subscriber2: {}",  d));

        multicastSink.emitNext(3, FAIL_FAST);
    }
}
