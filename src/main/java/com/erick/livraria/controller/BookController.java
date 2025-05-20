package com.erick.livraria.controller;

import com.erick.livraria.dto.book.CreateBookRequest;
import com.erick.livraria.dto.book.CreateBookResponse;
import com.erick.livraria.dto.book.UpdateBookRequest;
import com.erick.livraria.service.BookService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RestController
@RequestMapping("/api/v1/books")
@RequiredArgsConstructor
public class BookController {

    @Autowired
    private final BookService bookService;

    @Operation(summary = "Create a new book")
    @PostMapping
    public ResponseEntity<CreateBookResponse> create(@RequestBody @Validated CreateBookRequest request) {
        var response = bookService.createBook(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<CreateBookResponse>> findAll() {
        var list = bookService.findAllBooks();
        return ResponseEntity.ok(list);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CreateBookResponse> update(@PathVariable Long id,
                                                     @RequestBody @Validated UpdateBookRequest request) {
        var response = bookService.updateBook(id, request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        bookService.deleteBook(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    public ResponseEntity<List<CreateBookResponse>> search(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String author,
            @RequestParam(required = false) Integer publicationYear) {
        var result = bookService.searchBooks(title, author, publicationYear);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/all")
    public ResponseEntity<Void> deleteAllBooks() {
        bookService.deleteAllBooks();
        return ResponseEntity.noContent().build();
    }
}