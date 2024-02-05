package com.test.reactive.ch14.filter;

import com.test.reactive.SampleData;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

@Slf4j
public class TakeUntilExample {
    public static void main(String[] args) {
        // takeUntil조건에 걸릴때까지 데이터를 emit한다.
        Flux.fromIterable(SampleData.btcTopPricesPerYear)
                .takeUntil(t -> t.getT2() > 20_000_000)
                .subscribe(t -> log.info("# onNext: {}, {}", t.getT1(), t.getT2()));
    }
}
