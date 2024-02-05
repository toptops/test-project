package com.test.reactive.webFlux.v2;

import com.test.reactive.webFlux.core.BookDto;
import com.test.reactive.webFlux.v1.BookReactorMapper;
import com.test.reactive.webFlux.v1.BookReactorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

/**
 * 죄다 Mono로 동기적인거를 바꿔버리니.. 좀 헷갈린다. 이렇게 될 경우 복잡한 로직일때 예외처리라든지 힘들지 않을까?
 *
 */
@RestController
@RequestMapping("/v2/reactor/books")
@RequiredArgsConstructor
public class BookReactorV2Controller {
    private final BookReactorV2Service bookReactorService;
    private final BookReactorV2Mapper bookReactorMapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono postBook(@RequestBody Mono<BookDto.Post> requestBody) {
        Mono<BookReactorV2> result = bookReactorService.createBook(requestBody);

        return result.flatMap(book -> Mono.just(bookReactorMapper.bookToResponse(book)));
    }

    @PatchMapping("/{book-id}")
    public Mono patchBook(@PathVariable("book-id") long bookId,
                          @RequestBody Mono<BookDto.Patch> requestBody) {
        Mono<BookReactorV2> result = bookReactorService.updateBook(bookId, requestBody);
        return result.flatMap(book -> Mono.just(bookReactorMapper.bookToResponse(book)));
    }

    @GetMapping("/{book-id}")
    public Mono getBook(@PathVariable("book-id") long bookId) {
        return bookReactorService.findBook(bookId)
                .flatMap(book -> Mono.just(bookReactorMapper.bookToResponse(book)));
    }
}
