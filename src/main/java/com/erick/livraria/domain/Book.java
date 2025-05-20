package com.erick.livraria.domain;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "books",
        uniqueConstraints = @UniqueConstraint(name = "UK_BOOK_ISBN", columnNames = "isbn"))
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Book {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String author;

    @Column(name = "publication_year", nullable = false)
    private Integer publicationYear;

    @Column(nullable = false, length = 13)
    private String isbn;
}