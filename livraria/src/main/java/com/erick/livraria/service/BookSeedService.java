package com.erick.livraria.service;

public interface BookSeedService {
    void seedBooksFromGoogle(String query, int limit);
    void seedBooksFromOpenLibrary(String query, int limit);
}