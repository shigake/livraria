package com.erick.livraria.service.impl;


import com.erick.livraria.dto.book.CreateBookRequest;
import com.erick.livraria.dto.book.CreateBookResponse;
import com.erick.livraria.dto.book.UpdateBookRequest;
import com.erick.livraria.mapper.BookMapper;
import com.erick.livraria.repository.BookRepository;
import com.erick.livraria.service.BookService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.erick.livraria.repository.spec.BookSpecifications.*;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository repository;

    @Override
    @Transactional
    public CreateBookResponse createBook(CreateBookRequest request) {
        if (repository.existsByIsbn(request.isbn()))
            throw new IllegalArgumentException("ISBN already registered");

        var book = BookMapper.toEntity(request);
        return BookMapper.toResponse(repository.save(book));
    }

    @Override
    @Transactional(readOnly = true)
    public List<CreateBookResponse> findAllBooks() {
        return repository.findAll().stream()
                .map(BookMapper::toResponse)
                .toList();
    }

    @Override
    @Transactional
    public CreateBookResponse updateBook(Long id, UpdateBookRequest request) {
        var book = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Book not found"));

        book.setTitle(request.title());
        book.setAuthor(request.author());
        book.setPublicationYear(request.publicationYear());
        book.setIsbn(request.isbn());

        return BookMapper.toResponse(repository.save(book));
    }

    @Override
    @Transactional
    public void deleteBook(Long id) {
        if (!repository.existsById(id))
            throw new EntityNotFoundException("Book not found");

        repository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CreateBookResponse> searchBooks(String title, String author, Integer publicationYear) {
        var spec = Specification.where(titleContains(title))
                .and(authorContains(author))
                .and(hasPublicationYear(publicationYear));

        return repository.findAll(spec).stream()
                .map(BookMapper::toResponse)
                .toList();
    }

    @Override
    @Transactional
    public void deleteAllBooks() {
        repository.deleteAll();
    }
}
