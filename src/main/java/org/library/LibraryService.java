package org.library;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class LibraryService {

    private final Logger log = LoggerFactory.getLogger(LibraryService.class);
    BookClient bookClient;

    public LibraryService(BookClient bookClient) {
        this.bookClient = bookClient;
    }

    List<BookDTO> getBooksByClassification(String classification) {
        List<BookDTO> results = null;
        try {
            results = this.bookClient.getBooksByClassification(classification);
            return results;
        } catch (BookRetrievalException e) {
            log.error("Error getting books by classification {}", classification, e);
            return null;
        }
    }
}
