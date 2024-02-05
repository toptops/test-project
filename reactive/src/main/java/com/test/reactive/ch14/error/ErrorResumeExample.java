package com.test.reactive.ch14.error;

import com.test.reactive.Book;
import com.test.reactive.SampleData;
import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Publisher;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class ErrorResumeExample {
    public static void main(String[] args) {
        final String keyword = "DDD1";
        getBooksFromCache(keyword)
                .onErrorResume(e -> getBooksFromDatabase(keyword))
                .subscribe(d -> log.info("# onNext: {}", d.getBookName()),
                        e -> log.error("# onError: {}", e));
    }

    private static Flux<Book> getBooksFromDatabase(String keyword) {
        List<Book> books = new ArrayList<>(SampleData.books);
        books.add(new Book("DDD: Domain Driven Design", "Joy", "ddd-man", 35000, 200));
        return Flux.fromIterable(books)
                .filter(b -> b.getBookName().contains(keyword))
                .switchIfEmpty(Flux.error(new NoSuchBookException("No Such Book")));
    }

    private static Flux<Book> getBooksFromCache(String keyword) {
        return Flux.fromIterable(SampleData.books)
                .filter(b -> b.getBookName().contains(keyword))
                .switchIfEmpty(Flux.error(new NoSuchBookException("No such Book")));
    }

    private static class NoSuchBookException extends RuntimeException{
        public NoSuchBookException(String message) {
            super(message);
        }
    }
}
