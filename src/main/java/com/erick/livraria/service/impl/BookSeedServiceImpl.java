package com.erick.livraria.service.impl;

import com.erick.livraria.enuns.BookProvider;
import com.erick.livraria.factory.BookProviderFactory;
import com.erick.livraria.repository.BookRepository;
import com.erick.livraria.service.BookSeedService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.erick.livraria.enuns.BookProvider.*;

@Service
@RequiredArgsConstructor
public class BookSeedServiceImpl implements BookSeedService {

    private final BookRepository repository;
    private final BookProviderFactory providerFactory;

    @Override
    public void seedBooksFromGoogle(String query, int limit) {
        seed(query, limit, GOOGLE);
    }

    @Override
    public void seedBooksFromOpenLibrary(String query, int limit) {
        seed(query, limit, OPEN_LIBRARY);
    }

    private void seed(String query, int limit, BookProvider provider) {
        var books = providerFactory.resolve(provider)
                .fetchBooks(query, limit)
                .stream()
                .filter(book -> !repository.existsByIsbn(book.getIsbn()))
                .toList();

        repository.saveAll(books);
    }

}