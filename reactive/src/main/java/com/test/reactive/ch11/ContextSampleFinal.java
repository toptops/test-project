package com.test.reactive.ch11;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;
import reactor.util.context.Context;

@Slf4j
public class ContextSampleFinal {

    public static final String HEADER_AUTH_TOKEN = "authToken";
    public static void main(String[] args) {
        Mono<String> mono = postBook(Mono.just(new Book("abcd-1111-3533-2790", "Reactor's Bible", "Kevin")))
                .contextWrite(Context.of(HEADER_AUTH_TOKEN, "ehgjksalkq1"));

        mono.subscribe(d -> log.info("# onNext: {}", d));
    }

    private static Mono<String> postBook(Mono<Book> mono) {
        return Mono.zip(mono, Mono.deferContextual(ctx -> Mono.just(ctx.get(HEADER_AUTH_TOKEN))))
                .flatMap(t -> {
                    String response = "POST the book(" + t.getT1().getBookName() + ", " + t.getT1().getAuthor() + ") with token: " + t.getT2();
                    return Mono.just(response);
                });
    }


    @Data
    @AllArgsConstructor
    static class Book {
        private String isbn;
        private String bookName;
        private String author;
    }
}
