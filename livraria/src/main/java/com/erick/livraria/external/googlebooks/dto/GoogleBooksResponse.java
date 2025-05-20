package com.erick.livraria.external.googlebooks.dto;

import java.util.List;

public record GoogleBooksResponse(List<Item> items) {
    public record Item(VolumeInfo volumeInfo) {}
    public record VolumeInfo(
            String title,
            List<String> authors,
            String publishedDate,
            List<IndustryIdentifier> industryIdentifiers) {}

    public record IndustryIdentifier(String type, String identifier) {}
}