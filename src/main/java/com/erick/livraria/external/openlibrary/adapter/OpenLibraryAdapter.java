package com.erick.livraria.external.openlibrary.adapter;

import com.erick.livraria.domain.Book;
import com.erick.livraria.external.openlibrary.dto.OpenLibraryResponse;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
public class OpenLibraryAdapter {

    public Optional<Book> toBook(OpenLibraryResponse.Doc doc) {
        if (doc.title() == null || doc.author_name() == null) return Optional.empty();

        var isbn = (doc.isbn() != null && !doc.isbn().isEmpty())
                ? doc.isbn().get(0)
                : UUID.randomUUID().toString().substring(0, 13);

        var year = doc.first_publish_year() != null ? doc.first_publish_year() : 2000;

        var book = Book.builder()
                .title(doc.title())
                .author(String.join(", ", doc.author_name()))
                .publicationYear(year)
                .isbn(isbn)
                .build();

        return Optional.of(book);
    }
}