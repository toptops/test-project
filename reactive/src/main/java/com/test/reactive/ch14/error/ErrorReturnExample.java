package com.test.reactive.ch14.error;

import com.test.reactive.Book;
import com.test.reactive.SampleData;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

@Slf4j
public class ErrorReturnExample {
    public static void main(String[] args) {
        getBooks()
                .map(b -> b.getPenName().toUpperCase())
                .onErrorReturn("No pen name")
                .subscribe(log::info);
    }

    private static Flux<Book> getBooks() {
        return Flux.fromIterable(SampleData.books);
    }
}
