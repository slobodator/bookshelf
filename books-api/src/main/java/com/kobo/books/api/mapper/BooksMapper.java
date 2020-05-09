package com.kobo.books.api.mapper;

import com.kobo.books.api.dto.BookDto;
import com.kobo.books.api.dto.BookDtoV2;
import com.kobo.books.api.dto.CreateBookDto;
import com.kobo.books.model.Book;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface BooksMapper {
    BookDto toDto(Book book);

    @Mapping(target = "id", ignore = true)
    Book toModel(CreateBookDto dto);

    BookDtoV2 toDtoV2(Book book);
}
