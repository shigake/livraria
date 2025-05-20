package com.erick.livraria.dto.book;

public record CreateBookResponse(Long id,
                                 String title,
                                 String author,
                                 Integer publicationYear,
                                 String isbn) {
}