package org.library;

public class BookRetrievalException extends Throwable {
    public BookRetrievalException(String statusText) {
        super(statusText);
    }
}
