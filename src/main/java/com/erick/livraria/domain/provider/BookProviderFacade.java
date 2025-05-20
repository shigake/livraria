package com.erick.livraria.domain.provider;

import com.erick.livraria.domain.Book;

import java.util.List;

public interface BookProviderFacade {
    List<Book> fetchBooks(String query, int limit);
}
