package com.erick.livraria.factory;

import com.erick.livraria.domain.provider.BookProviderFacade;
import com.erick.livraria.enuns.BookProvider;
import com.erick.livraria.external.googlebooks.facade.GoogleBooksFacade;
import com.erick.livraria.external.openlibrary.facade.OpenLibraryFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import static com.erick.livraria.enuns.BookProvider.GOOGLE;
import static com.erick.livraria.enuns.BookProvider.OPEN_LIBRARY;

@Component
@RequiredArgsConstructor
public class BookProviderFactory {

    private final GoogleBooksFacade google;
    private final OpenLibraryFacade openLibrary;

    public BookProviderFacade resolve(BookProvider provider) {
        return switch (provider) {
            case GOOGLE -> google;
            case OPEN_LIBRARY -> openLibrary;
        };
    }
}