package ru.developeerz.gateway.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebConfig {

    @Bean
    WebClient userWebClient(@Value("${service.user}") String url) {
        return WebClient.create(url);
    }

    @Bean
    WebClient bookingWebClient(@Value("${service.booking}") String url) {
        return WebClient.create(url);
    }
}
