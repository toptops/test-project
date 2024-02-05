package com.test.reactive.webFlux.core;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

public class BookDto {
    @Builder
    @Getter
    public static class ResponseV1 {
        private long bookId;
        private String bookName;
        private String author;
        private String isbn;
    }

    @Getter
    public static class Post {
        private String titleKorean;
        private String titleEnglish;
        private String description;
        private String author;
        private String isbn;
        private String publishDate;
    }

    @Getter
    public static class Patch {
        @Setter
        private long bookId;
        private String titleKorean;
        private String titleEnglish;
        private String description;
        private String author;
        private String isbn;
        private String publishDate;
    }

    @Builder
    @Getter
    public static class ResponseV2 {
        private long bookId;
        private String titleKorean;
        private String titleEnglish;
        private String description;
        private String author;
        private String isbn;
        private String publishDate;
    }
}
