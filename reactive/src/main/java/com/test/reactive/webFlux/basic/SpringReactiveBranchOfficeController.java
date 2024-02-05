package com.test.reactive.webFlux.basic;

import com.test.reactive.Book;
import com.test.reactive.SampleData;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.mapping;

@Slf4j
@RequestMapping(path = "/v1/books", produces = {MediaType.APPLICATION_JSON_VALUE})
@RestController
public class SpringReactiveBranchOfficeController {
    private final Map<Long, Book> bookMap;

    public SpringReactiveBranchOfficeController() {
        this.bookMap = new HashMap<>();
        Book book1 = new Book("TestBook", "Test", "TestPen", 1000, 10);
        bookMap.put(1L, book1);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{book-id}")
    public Mono<Book> getBook(@PathVariable("book-id") long bookId) throws InterruptedException {
        Thread.sleep(200);
        Book book = bookMap.get(bookId);
        log.info("# book for response: {}, {}", bookId, book.getBookName());
        return Mono.just(book);
    }
}
