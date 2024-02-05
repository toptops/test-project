package com.test.reactive.ch14.filter;

import com.test.reactive.SampleData;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

@Slf4j
public class Filter2Example {
    public static void main(String[] args) {
        Flux.fromIterable(SampleData.btcTopPricesPerYear)
                .filter(t -> t.getT2() > 20_000_000)
                .subscribe(d -> log.info("{} : {}", d.getT1(), d.getT2()));
    }
}
