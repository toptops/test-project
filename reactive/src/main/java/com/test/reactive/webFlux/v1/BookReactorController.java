package com.test.reactive.webFlux.v1;


import com.test.reactive.webFlux.core.BookDto;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/v1/reactor/books")
@RequiredArgsConstructor
public class BookReactorController {
    private final BookReactorService bookReactorService;
    private final BookReactorMapper bookReactorMapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono postBook(@RequestBody BookDto.Post request) {
        Mono<BookReactor> book = bookReactorService.createBook(bookReactorMapper.bookPostToBook(request));
        Mono<BookDto.ResponseV2> response = bookReactorMapper.bookToBookResponse(book);
        return response;
    }

    @PatchMapping("/{book-id}")
    public Mono patchBook(@PathVariable("book-id") long bookId,
                          @RequestBody BookDto.Patch request) {
        request.setBookId(bookId);
        Mono<BookReactor> book = bookReactorService.updateBook(bookReactorMapper.bookPatchToBook(request));
        return bookReactorMapper.bookToBookResponse(book);
    }

    @GetMapping("/{book-id}")
    public Mono getBook(@PathVariable("book-id") long bookId) {
        Mono<BookReactor> book = bookReactorService.findBook(bookId);
        return bookReactorMapper.bookToBookResponse(book);
    }

}
