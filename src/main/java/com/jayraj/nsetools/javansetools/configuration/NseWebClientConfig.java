package com.jayraj.nsetools.javansetools.configuration;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class NseWebClientConfig {
    @Bean
    @Qualifier("nseArchiveClient")
    public WebClient getNSEArchivesClient() {
        return WebClient.builder().baseUrl("https://archives.nseindia.com/content/equities/EQUITY_L.csv").build();
    }

    @Bean
    @Qualifier("nseAPIClient")
    public WebClient getNseAPIClient() {
        return WebClient.builder().baseUrl("https://www.nseindia.com/api").build();
    }
}
