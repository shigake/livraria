package com.erick.livraria.external.googlebooks.client;

import com.erick.livraria.external.googlebooks.dto.GoogleBooksResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class GoogleBooksClient {

    private final RestTemplate restTemplate = new RestTemplate();

    public GoogleBooksResponse fetch(String query, int limit) {
        String url = "https://www.googleapis.com/books/v1/volumes?q=" + query + "&maxResults=" + limit;
        return restTemplate.getForObject(url, GoogleBooksResponse.class);
    }
}