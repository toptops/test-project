package com.test.reactive.webFlux.v1;

import com.test.reactive.webFlux.core.BookDto;
import org.mapstruct.Mapper;
import reactor.core.publisher.Mono;

@Mapper(componentModel = "spring")
public interface BookReactorMapper {
    BookReactor bookPostToBook(BookDto.Post requestBody);
    BookReactor bookPatchToBook(BookDto.Patch requestBody);
    BookDto.ResponseV2 bookToResponse(BookReactor book);
    default Mono<BookDto.ResponseV2> bookToBookResponse(Mono<BookReactor> mono) {
        return mono.flatMap(book -> Mono.just(bookToResponse(book)));
    }
}
