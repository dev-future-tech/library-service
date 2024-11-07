package org.library;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("book.service")
public class BookClientProperties {
    private final String url;

    public BookClientProperties(String url) {
        this.url = url;
    }
    public String getUrl() {
        return this.url;
    }
}
