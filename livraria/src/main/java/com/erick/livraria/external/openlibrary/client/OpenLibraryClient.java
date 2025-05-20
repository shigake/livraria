package com.erick.livraria.external.openlibrary.client;


import com.erick.livraria.external.openlibrary.dto.OpenLibraryResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class OpenLibraryClient {

    private final RestTemplate restTemplate = new RestTemplate();

    public OpenLibraryResponse fetch(String query, int limit) {
        String url = "https://openlibrary.org/search.json?q=" + query + "&limit=" + limit;
        return restTemplate.getForObject(url, OpenLibraryResponse.class);
    }
}
