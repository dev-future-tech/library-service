package org.library;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class LibraryConfiguration {

    @Bean
    public RestClient restClient() {
        return RestClient.create();
    }
}
