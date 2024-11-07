package org.library;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/library")
public class LibraryController {

    LibraryService libraryService;

    public LibraryController(LibraryService libraryService) {
        this.libraryService = libraryService;
    }

    private final Logger log = LoggerFactory.getLogger(LibraryController.class);


    @GetMapping()
    public ResponseEntity<List<BookDTO>> getShelf(@RequestParam("classification") String classification) {
        List<BookDTO> results = this.libraryService.getBooksByClassification(classification);
        if(results == null) {
            return ResponseEntity.badRequest().build();
        }
        if(results.isEmpty()) {
            return ResponseEntity.ok(Collections.emptyList());
        }

        return ResponseEntity.ok(results);
    }
}
