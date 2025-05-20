package com.erick.livraria.service;

import com.erick.livraria.dto.book.CreateBookRequest;
import com.erick.livraria.dto.book.CreateBookResponse;
import com.erick.livraria.dto.book.UpdateBookRequest;

import java.util.List;

public interface BookService {
    CreateBookResponse createBook(CreateBookRequest request);
    List<CreateBookResponse> findAllBooks();
    CreateBookResponse updateBook(Long id, UpdateBookRequest request);
    void deleteBook(Long id);
    List<CreateBookResponse> searchBooks(String title, String author, Integer publicationYear);
    void deleteAllBooks();


}
