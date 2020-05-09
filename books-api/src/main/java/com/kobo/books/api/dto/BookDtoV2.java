package com.kobo.books.api.dto;

import lombok.Data;

@Data
public class BookDtoV2 {
    private String isbn;
    private String title;
    private String author;
    private String description;
}
