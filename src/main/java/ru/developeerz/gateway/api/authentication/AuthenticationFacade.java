package ru.developeerz.gateway.api.authentication;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import ru.developeerz.gateway.api.authentication.model.AuthenticationRequest;
import ru.developeerz.gateway.api.authentication.model.AuthenticationResponse;

@Service
@RequiredArgsConstructor
public class AuthenticationFacade {

    private final WebClient userWebClient;

    public AuthenticationResponse getAuthentication(AuthenticationRequest request) {

        return userWebClient.post()
                .uri("/api/user/authentication")
                .bodyValue(request)
                .retrieve()
                .bodyToMono(AuthenticationResponse.class)
                .block();
    }
}
