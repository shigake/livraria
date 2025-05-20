package com.erick.livraria.external.openlibrary.dto;

import java.util.List;

public record OpenLibraryResponse(List<Doc> docs) {

    public record Doc(
            String title,
            List<String> author_name,
            Integer first_publish_year,
            List<String> isbn
    ) {}
}
