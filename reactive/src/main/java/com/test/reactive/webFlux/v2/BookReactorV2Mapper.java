package com.test.reactive.webFlux.v2;

import com.test.reactive.webFlux.core.BookDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface BookReactorV2Mapper {
    BookReactorV2 bookPostToBook(BookDto.Post requestBody);
    BookReactorV2 bookPatchToBook(BookDto.Patch requestBody);
    BookDto.ResponseV2 bookToResponse(BookReactorV2 book);
}
