package com.kobo.books.api.dto;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateBookDto {
    @NotNull
    private String isbn;

    @NotNull
    private String title;

    @NotNull
    private String author;

    private String description;
}
