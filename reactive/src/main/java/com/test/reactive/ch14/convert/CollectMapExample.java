package com.test.reactive.ch14.convert;

import com.test.reactive.SampleData;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

@Slf4j
public class CollectMapExample {
    public static void main(String[] args) {
        Flux.range(0, 26)
                .collectMap(k -> SampleData.morseCodes[k],
                        v -> transformToLetter(v))
                .subscribe(m -> log.info("# onNext: {}", m));
    }

    private static String transformToLetter(int value) {
        return Character.toString((char)('a' + value));
    }
}
