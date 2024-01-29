package com.test.reactive.ch11;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@Slf4j
public class ContextViewSample {
    public static void main(String[] args) throws InterruptedException {
        final String key1 = "company";
        final String key2 = "firstName";
        final String key3 = "lastName";

        Mono.deferContextual(ctx -> Mono.just(ctx.get(key1) + ", "
                + ctx.getOrEmpty(key2).orElse("No firstName") + " "
                + ctx.getOrDefault(key3, "No lastName")))
                .publishOn(Schedulers.parallel())
                .contextWrite(c -> c.put(key1, "Apple"))
                .subscribe(d -> log.info("# onNext: {}", d));
        Thread.sleep(100L);
    }
}
