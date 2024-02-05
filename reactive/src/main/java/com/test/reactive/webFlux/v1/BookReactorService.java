package com.test.reactive.webFlux.v1;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@Service
public class BookReactorService {
    public Mono<BookReactor> createBook(BookReactor book) {
        // not implement business logic;
        return Mono.just(book);
    }

    public Mono<BookReactor> updateBook(BookReactor book) {
        // not implement business logic;
        return Mono.just(book);
    }

    public Mono<BookReactor> findBook(long bookId) {
        return Mono.just(
                new BookReactor(bookId,
                        "Java 고급",
                        "Advanced Java",
                        "Kevin",
                        "111-11-1111-111-1",
                        "Java 중급 프로그래밍 마스터",
                        "2022-03-22",
                        LocalDateTime.now(),
                        LocalDateTime.now())
        );
    }
}
