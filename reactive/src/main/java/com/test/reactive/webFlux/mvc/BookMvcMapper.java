package com.test.reactive.webFlux.mvc;

import com.test.reactive.Book;
import com.test.reactive.webFlux.core.BookDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BookMvcMapper {
    BookMvc bookPostToBook(BookDto.Post requestBody);
    BookMvc bookPatchToBook(BookDto.Patch requestBody);
    BookDto.ResponseV2 bookToBookResponse(BookMvc book);
}
