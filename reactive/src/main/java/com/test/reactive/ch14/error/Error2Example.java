package com.test.reactive.ch14.error;

import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Publisher;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.zip.DataFormatException;

@Slf4j
public class Error2Example {
    public static void main(String[] args) {
        Flux.just('a', 'b', 'c', '3', 'd')
                .flatMap(l -> {
                    try {
                        return convert(l);
                    } catch (DataFormatException e) {
                        return Flux.error(e);
                    }
                })
                .subscribe(d -> log.info("# onNext: {}", d),
                        e -> log.error("# onError: {}", e));

    }

    private static Mono<String> convert(char ch) throws DataFormatException {
        if(!Character.isAlphabetic(ch)) {
            throw new DataFormatException("Not Alphabetic");
        }
        return Mono.just("Converted to " + Character.toUpperCase(ch));
    }
}
