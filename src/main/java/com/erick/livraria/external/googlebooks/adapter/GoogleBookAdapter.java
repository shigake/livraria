package com.erick.livraria.external.googlebooks.adapter;


import com.erick.livraria.domain.Book;
import com.erick.livraria.external.googlebooks.dto.GoogleBooksResponse;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

import static com.erick.livraria.util.GoogleBookUtil.parseYear;

@Component
public class GoogleBookAdapter {

    public Optional<Book> toBook(GoogleBooksResponse.Item item) {
        var info = item.volumeInfo();
        if (info == null || info.title() == null || info.authors() == null) return Optional.empty();

        var isbn = Optional.ofNullable(info.industryIdentifiers())
                .flatMap(list -> list.stream()
                        .filter(id -> "ISBN_13".equals(id.type()))
                        .map(GoogleBooksResponse.IndustryIdentifier::identifier)
                        .findFirst())
                .orElse(UUID.randomUUID().toString().substring(0, 13));

        var year = parseYear(info.publishedDate());

        var book = Book.builder()
                .title(info.title())
                .author(String.join(", ", info.authors()))
                .publicationYear(year)
                .isbn(isbn)
                .build();

        return Optional.of(book);
    }


}