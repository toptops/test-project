package com.test.reactive.webFlux.mvc;

import com.test.reactive.webFlux.core.BookDto;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/mvc/books")
@RequiredArgsConstructor
public class BookMvcController {
    private final BookMvcService bookMvcService;
    private final BookMvcMapper bookMapper;

    @PostMapping
    public ResponseEntity postBook(@RequestBody BookDto.Post request) {
        BookMvc book = bookMvcService.createBook(bookMapper.bookPostToBook(request));
        return ResponseEntity.ok(bookMapper.bookToBookResponse(book));
    }

    @PatchMapping("/{book-id}")
    public ResponseEntity patchBook(@PathVariable("book-id") long bookId,
                                    @RequestBody BookDto.Patch request) {
        BookMvc book = bookMvcService.updateBook(bookMapper.bookPatchToBook(request));
        return ResponseEntity.ok(bookMapper.bookToBookResponse(book));
    }

    @GetMapping("/{book-id}")
    public ResponseEntity getBook(@PathVariable("book-id") long bookId) {
        BookMvc book = bookMvcService.findBook(bookId);
        return ResponseEntity.ok(bookMapper.bookToBookResponse(book));
    }
}
