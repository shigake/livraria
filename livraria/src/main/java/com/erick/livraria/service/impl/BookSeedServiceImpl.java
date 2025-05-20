package com.erick.livraria.service.impl;

import com.erick.livraria.domain.Book;
import com.erick.livraria.external.googlebooks.dto.GoogleBooksResponse;
import com.erick.livraria.external.googlebooks.adapter.GoogleBookAdapter;
import com.erick.livraria.external.googlebooks.facade.GoogleBooksFacade;
import com.erick.livraria.external.openlibrary.facade.OpenLibraryFacade;
import com.erick.livraria.repository.BookRepository;
import com.erick.livraria.service.BookSeedService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookSeedServiceImpl implements BookSeedService {

    private final BookRepository repository;
    private final GoogleBooksFacade googleBooksFacade;
    private final OpenLibraryFacade openLibraryFacade;
    @Override
    public void seedBooksFromGoogle(String query, int limit) {
        var books = googleBooksFacade.fetchBooks(query, limit).stream()
                .filter(book -> !repository.existsByIsbn(book.getIsbn()))
                .toList();

        repository.saveAll(books);
    }

    @Override
    public void seedBooksFromOpenLibrary(String query, int limit) {
        var books = openLibraryFacade.fetchBooks(query, limit).stream()
                .filter(book -> !repository.existsByIsbn(book.getIsbn()))
                .toList();

        repository.saveAll(books);
    }

}