package com.test.reactive.webFlux.router;

import com.test.reactive.webFlux.core.BookDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface BookRouterMapper {
    Book bookPostToBook(BookDto.Post requestBody);
    Book bookPatchToBook(BookDto.Patch requestBody);
    BookDto.ResponseV2 bookToResponse(Book book);
    List<BookDto.ResponseV2> booksToResponse(List<Book> books);
}
