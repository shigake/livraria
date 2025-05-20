package com.erick.livraria.mapper;

import com.erick.livraria.domain.Book;
import com.erick.livraria.dto.book.CreateBookRequest;
import com.erick.livraria.dto.book.CreateBookResponse;

public class BookMapper {

    private BookMapper() {}

    public static Book toEntity(CreateBookRequest dto) {
        return Book.builder()
                .title(dto.title())
                .author(dto.author())
                .publicationYear(dto.publicationYear())
                .isbn(dto.isbn())
                .build();
    }

    public static CreateBookResponse toResponse(Book entity) {
        return new CreateBookResponse(entity.getId(),
                entity.getTitle(),
                entity.getAuthor(),
                entity.getPublicationYear(),
                entity.getIsbn());
    }
}
