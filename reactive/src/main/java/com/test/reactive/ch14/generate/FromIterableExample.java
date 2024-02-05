package com.test.reactive.ch14.generate;

import com.test.reactive.SampleData;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;


@Slf4j
public class FromIterableExample {
    public static void main(String[] args) {
        Flux.fromIterable(SampleData.coins)
                .subscribe(c -> log.info("coin 명: {}, 현재가: {}", c.getT1(), c.getT2()));
    }
}
