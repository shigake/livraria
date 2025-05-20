package com.erick.livraria.repository.spec;

import com.erick.livraria.domain.Book;
import org.springframework.data.jpa.domain.Specification;

public class BookSpecifications {
    public static Specification<Book> titleContains(String title) {
        return (root, query, cb) -> title == null
                ? null
                : cb.like(cb.lower(root.get("title")), "%" + title.toLowerCase() + "%");
    }

    public static Specification<Book> authorContains(String author) {
        return (root, query, cb) -> author == null
                ? null
                : cb.like(cb.lower(root.get("author")), "%" + author.toLowerCase() + "%");
    }

    public static Specification<Book> hasPublicationYear(Integer year) {
        return (root, query, cb) -> year == null
                ? null
                : cb.equal(root.get("publicationYear"), year);
    }
}
