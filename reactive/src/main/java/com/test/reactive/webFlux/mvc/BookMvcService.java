package com.test.reactive.webFlux.mvc;

import com.test.reactive.Book;
import org.springframework.stereotype.Service;

@Service
public class BookMvcService {
    public BookMvc createBook(BookMvc book) {
        // not implement business logic;
        return book;
    }

    public BookMvc updateBook(BookMvc book) {
        // not implement business logic;
        return book;
    }

    public BookMvc findBook(long bookId) {
        return BookMvc.builder()
                .bookId(bookId)
                .titleKorean("Java 고급")
                .titleEnglish("Advanced Java")
                .author("Kevin")
                .isbn("111-11-1111-111-1")
                .description("Java 중급 프로그래밍 마스터")
                .publishDate("2022-03-22")
                .build();
    }
}
