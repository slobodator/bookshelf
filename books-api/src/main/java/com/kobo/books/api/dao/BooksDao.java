package com.kobo.books.api.dao;

import java.util.Optional;

import com.kobo.books.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BooksDao extends JpaRepository<Book, String> {
    Optional<Book> findByIsbn(String isbn);
}
