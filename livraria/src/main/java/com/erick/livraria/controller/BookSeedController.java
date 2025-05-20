package com.erick.livraria.controller;

import com.erick.livraria.service.BookSeedService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/seed")
@RequiredArgsConstructor
public class BookSeedController {

    private final BookSeedService service;

    @PostMapping("/google")
    public ResponseEntity<Void> seedFromGoogle(@RequestParam(defaultValue = "programming") String query,
                                               @RequestParam(defaultValue = "10") int limit) {
        service.seedBooksFromGoogle(query, limit);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/open")
    public ResponseEntity<Void> seedFromOpenLibrary(@RequestParam(defaultValue = "programming") String query,
                                                    @RequestParam(defaultValue = "10") int limit) {
        service.seedBooksFromOpenLibrary(query, limit);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/all")
    public ResponseEntity<Void> seedFromAll(@RequestParam(defaultValue = "programming") String query,
                                            @RequestParam(defaultValue = "10") int limit) {
        service.seedBooksFromGoogle(query, limit);
        service.seedBooksFromOpenLibrary(query, limit);
        return ResponseEntity.noContent().build();
    }
}