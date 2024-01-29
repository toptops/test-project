package com.test.reactive.ch11;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@Slf4j
public class ContextSample3 {
    public static void main(String[] args) throws InterruptedException {
        String key1 = "company";
        Mono.just("Steve")
                .flatMap(name -> Mono.deferContextual(ctx ->
                        Mono.just(ctx.get(key1) + ", " + name)
                                .transformDeferredContextual(
                                        (mono, innerctx) -> mono.map(data -> data + ", " + innerctx.get("role"))))
                        .contextWrite(context -> context.put("role", "CEO"))
                ).publishOn(Schedulers.parallel())
                .contextWrite(context ->  context.put(key1, "Apple"))
                .subscribe(d -> log.info("# onNext: {}", d));
        Thread.sleep(100L);
    }
}
