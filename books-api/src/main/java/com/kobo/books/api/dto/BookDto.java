package com.kobo.books.api.dto;

import lombok.Data;

@Data
public class BookDto {
    private String isbn;
    private String title;
    private String author;
}
