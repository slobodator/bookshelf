package com.kobo.books.api.service;

import java.util.Optional;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

import com.kobo.books.api.dao.BooksDao;
import com.kobo.books.api.dto.BookDto;
import com.kobo.books.api.dto.BookDtoV2;
import com.kobo.books.api.dto.CreateBookDto;
import com.kobo.books.api.dto.UpdateBookDto;
import com.kobo.books.api.mapper.BooksMapper;
import com.kobo.books.model.Book;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class BooksServiceImpl implements BooksService {
    private final BooksDao booksDao;
    private final BooksMapper booksMapper;

    @PostConstruct
    public void init() {
        booksDao.save(new Book("9780007322497", "The Fellowship of the Ring: The Lord of the Rings", "J. R. R. Tolkien"));
        booksDao.save(new Book("9781119235583", "Java For Dummies", "Barry A. Burd"));
        booksDao.save(new Book("9780450411434", "It", "Steven King"));
        booksDao.save(new Book("9783842102217", "Das tolino-Buch", "Christine Peyton"));
    }

    @Override
    public Optional<BookDto> getBook(String isbn) {
        return booksDao.findByIsbn(isbn)
                .map(booksMapper::toDto);
    }

    @Override
    public Page<BookDto> getAllBooks(Pageable pageable) {
        return booksDao.findAll(pageable)
                .map(booksMapper::toDto);
    }

    @Override
    public BookDto createBook(CreateBookDto createBookDto) {
        Book book = booksMapper.toModel(createBookDto);
        Book savedBook = booksDao.save(book);
        return booksMapper.toDto(savedBook);
    }

    @Override
    public Optional<BookDto> deleteBook(String isbn) {
        return booksDao.findByIsbn(isbn)
                .map(b -> {
                    BookDto bookDto = booksMapper.toDto(b);
                    booksDao.delete(b);
                    return bookDto;
                });
    }

    @Override
    public Optional<BookDto> updateBook(String isbn, UpdateBookDto updateDto) {
        return booksDao.findByIsbn(isbn)
                .map(b -> {
                    b.setAuthor(updateDto.getAuthor());
                    b.setTitle(updateDto.getTitle());
                    if (updateDto.getDescription() != null) {
                        b.setDescription(updateDto.getDescription());
                    }
                    return booksMapper.toDto(b);
                });
    }

    @Override
    public Optional<BookDtoV2> getBookV2(String isbn) {
        return booksDao.findByIsbn(isbn)
                .map(booksMapper::toDtoV2);
    }

    @Override
    public Page<BookDtoV2> getAllBooksV2(Pageable pageable) {
        return booksDao.findAll(pageable)
                .map(booksMapper::toDtoV2);
    }
}
