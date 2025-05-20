package com.erick.livraria.external.openlibrary.facade;

import com.erick.livraria.domain.Book;
import com.erick.livraria.domain.provider.BookProviderFacade;
import com.erick.livraria.external.openlibrary.adapter.OpenLibraryAdapter;
import com.erick.livraria.external.openlibrary.client.OpenLibraryClient;
import com.erick.livraria.external.openlibrary.dto.OpenLibraryResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import java.util.Optional;
import java.util.List;

@Component
@RequiredArgsConstructor
public class OpenLibraryFacade implements BookProviderFacade {

    private final OpenLibraryClient client;
    private final OpenLibraryAdapter adapter;

    public List<Book> fetchBooks(String query, int limit) {
        OpenLibraryResponse response = client.fetch(query, limit);
        if (response == null || response.docs() == null) return List.of();

        return response.docs().stream()
                .map(adapter::toBook)
                .flatMap(Optional::stream)
                .toList();
    }
}