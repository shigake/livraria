package com.erick.livraria.external.googlebooks.facade;

import com.erick.livraria.domain.Book;
import com.erick.livraria.domain.provider.BookProviderFacade;
import com.erick.livraria.external.googlebooks.adapter.GoogleBookAdapter;
import com.erick.livraria.external.googlebooks.client.GoogleBooksClient;
import com.erick.livraria.external.googlebooks.dto.GoogleBooksResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import java.util.Optional;
import java.util.List;

@Component
@RequiredArgsConstructor
public class GoogleBooksFacade implements BookProviderFacade {

    private final GoogleBooksClient client;
    private final GoogleBookAdapter adapter;

    @Override
    public List<Book> fetchBooks(String query, int limit) {
        var response = client.fetch(query, limit);
        if (response == null || response.items() == null) return List.of();

        return response.items().stream()
                .map(adapter::toBook)
                .flatMap(Optional::stream)
                .toList();
    }
}