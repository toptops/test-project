package com.test.reactive.ch14.generate;

import com.test.reactive.SampleData;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

@Slf4j
public class FromSteamExample {
    public static void main(String[] args) {
        Flux.fromStream(() -> SampleData.coinNames.stream())
                .filter(c -> c.equals("BTC") || c.equals("ETH"))
                .subscribe(d -> log.info("{}", d));
    }
}
