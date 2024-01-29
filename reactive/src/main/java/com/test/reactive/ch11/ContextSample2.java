package com.test.reactive.ch11;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@Slf4j
public class ContextSample2 {
    public static void main(String[] args) throws InterruptedException {
        final String key1 = "company";

        Mono<String> mono = Mono.deferContextual(ctx -> Mono.just("Company: " + " " + ctx.get(key1)))
                .publishOn(Schedulers.parallel());

        mono.contextWrite(context -> context.put(key1, "Apple"))
                .subscribe(d -> log.info("# Subscribe1 onNext: {}", d));

        mono.contextWrite(context -> context.put(key1, "Microsoft"))
                .subscribe(d -> log.info("# Subscribe2 onNext: {}", d));

        Thread.sleep(1000);
    }
}
