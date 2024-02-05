package com.test.reactive.ch14.error;

import com.test.reactive.Book;
import com.test.reactive.SampleData;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.stream.Collectors;

@Slf4j
public class ErrorRetry2Example {
    public static void main(String[] args) throws InterruptedException {
        getBooks()
                .collect(Collectors.toSet())
                .subscribe(b -> b.stream().forEach(b1 -> log.info("book name: {}, price: {}", b1.getBookName(), b1.getPrice())));
        Thread.sleep(12000);
    }

    private static Flux<Book> getBooks() {
        final int[] count = {0};
        return Flux.fromIterable(SampleData.books)
                .delayElements(Duration.ofMillis(500))
                .map(b -> {
                    try {
                        count[0]++;
                        if(count[0] == 3) {
                            Thread.sleep(2000);
                        }
                    } catch (InterruptedException e) {}
                    return b;
                })
                .timeout(Duration.ofSeconds(2))
                .retry(1)
                .doOnNext(b -> log.info("# getBooks > doOnNext: {}, price: {}", b.getBookName(), b.getPrice()));
    }
}
