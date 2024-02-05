package com.test.reactive.ch14.filter;

import com.test.reactive.SampleData;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

@Slf4j
public class TakeWhileExample {
    public static void main(String[] args) {
        // until과 다른점은 조건에 걸린 데이터를 포함안한다.
        Flux.fromIterable(SampleData.btcTopPricesPerYear)
                .takeWhile(t -> t.getT2() < 20_000_000)
                .subscribe(t -> log.info("# onNext: {}, {}", t.getT1(), t.getT2()));
    }
}
