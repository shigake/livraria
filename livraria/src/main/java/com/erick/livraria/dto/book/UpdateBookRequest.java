package com.erick.livraria.dto.book;

import jakarta.validation.constraints.*;

public record UpdateBookRequest(
        @NotBlank String title,
        @NotBlank String author,
        @NotNull @Min(1450) @Max(2050) Integer publicationYear,
        @NotBlank @Pattern(regexp = "\\d{10}|\\d{13}") String isbn) {
}