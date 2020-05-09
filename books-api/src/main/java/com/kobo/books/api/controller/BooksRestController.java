package com.kobo.books.api.controller;

import javax.validation.Valid;

import com.kobo.books.api.dto.BookDto;
import com.kobo.books.api.dto.BookDtoV2;
import com.kobo.books.api.dto.CreateBookDto;
import com.kobo.books.api.dto.UpdateBookDto;
import com.kobo.books.api.service.BooksService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class BooksRestController {
    private final BooksService booksService;

    @GetMapping("/books/{isbn}")
    public ResponseEntity<BookDto> getBook(@PathVariable("isbn") String isbn) {
        return booksService.getBook(isbn)
                .map(b -> new ResponseEntity<>(b, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/books/")
    public ResponseEntity<Page<BookDto>> getAllBooks(Pageable pageable) {
        Page<BookDto> result = booksService.getAllBooks(pageable);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping(value = "/books")
    public ResponseEntity<BookDto> createBook(@Valid @RequestBody CreateBookDto createBookDto) {
        BookDto createdBookDto = booksService.createBook(createBookDto);
        return new ResponseEntity<>(createdBookDto, HttpStatus.OK); // better HttpStatus.CREATED
    }

    @PutMapping(value = "/books/{isbn}")
    public ResponseEntity<BookDto> updateBook(
            @PathVariable("isbn") String isbn,
            @Valid @RequestBody UpdateBookDto updateDto
    ) {
        return booksService.updateBook(isbn, updateDto)
                .map(b -> new ResponseEntity<>(b, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/books/{isbn}")
    public ResponseEntity<BookDto> deleteBook(@PathVariable("isbn") String isbn) {
        return booksService.deleteBook(isbn)
                .map(b -> new ResponseEntity<>(b, HttpStatus.NO_CONTENT))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/v2/books/{isbn}")
    public ResponseEntity<BookDtoV2> getBookV2(@PathVariable("isbn") String isbn) {
        return booksService.getBookV2(isbn)
                .map(b -> new ResponseEntity<>(b, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("v2/books/")
    public ResponseEntity<Page<BookDtoV2>> getAllBooksV2(Pageable pageable) {
        Page<BookDtoV2> result = booksService.getAllBooksV2(pageable);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}