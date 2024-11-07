package org.library;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class BookClient {
    private final Logger log = LoggerFactory.getLogger(BookClient.class);

    final String BOOK_URI;

    public BookClient(@Value("${book.service.url}") String bookUri) {
        this.BOOK_URI = bookUri;
    }

    List<BookDTO> getBooks() {
        log.debug("BOOK_URI {}", BOOK_URI);
        RestClient restClient = RestClient.create(this.BOOK_URI);

        List<BookDTO> books = restClient
                .get()
                .uri("/api/book")
                .retrieve()
                .body(new ParameterizedTypeReference<List<BookDTO>>() {});
        return books;
    }

    List<BookDTO> getBooksByClassification(String classification) throws BookRetrievalException {
        RestClient restClient = RestClient.create(this.BOOK_URI);

        Map<String, String> uriVariables = new HashMap<>();
        uriVariables.put("classification", classification);

        return restClient
                .get()
                .uri("/api/book?classification={classification}", uriVariables)
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, (request, response) -> {
                    log.error("Error finding books to classification {}", classification);
                })
                .body(new ParameterizedTypeReference<>() {});
    }
}
