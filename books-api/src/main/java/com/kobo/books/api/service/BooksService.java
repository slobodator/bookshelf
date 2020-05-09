package com.kobo.books.api.service;

import java.util.Optional;

import com.kobo.books.api.dto.BookDto;
import com.kobo.books.api.dto.BookDtoV2;
import com.kobo.books.api.dto.CreateBookDto;
import com.kobo.books.api.dto.UpdateBookDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BooksService {
    Optional<BookDto> getBook(String isbn);

    Page<BookDto> getAllBooks(Pageable pageable);

    BookDto createBook(CreateBookDto createBookDto);

    Optional<BookDto> deleteBook(String isbn);

    Optional<BookDto> updateBook(String isbn, UpdateBookDto updateDto);

    Optional<BookDtoV2> getBookV2(String isbn);

    Page<BookDtoV2> getAllBooksV2(Pageable pageable);
}
