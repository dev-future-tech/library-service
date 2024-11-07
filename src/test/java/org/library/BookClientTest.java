package org.library;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class BookClientTest {
    private final Logger log = LoggerFactory.getLogger(BookClientTest.class);

    @Autowired
    BookClient bookClient;

    @Test
    public void testGetBooks() throws Exception {
        log.info("Getting books...");
        List<BookDTO> books = bookClient.getBooks();
        Assertions.assertThat(books).isNotEmpty();

        log.info("Total books is {}", books.size());
    }
}
