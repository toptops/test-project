package com.test.reactive.ch14.convert;

import com.test.reactive.SampleData;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

import static java.util.stream.Collectors.joining;

@Slf4j
public class CollectListExample {
    public static void main(String[] args) {
        Flux.just("...", "---", "...")
                .map(c -> transformMorseCode(c))
                .collectList()
                .subscribe(l -> log.info(l.stream().collect(joining())));
    }

    private static String transformMorseCode(String morseCode) {
        return SampleData.morseCodeMap.get(morseCode);
    }
}
