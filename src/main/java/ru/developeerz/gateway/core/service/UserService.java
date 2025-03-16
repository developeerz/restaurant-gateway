package ru.developeerz.gateway.core.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import ru.developeerz.gateway.api.user.model.JwtResponse;
import ru.developeerz.gateway.api.user.model.LoginRequest;
import ru.developeerz.gateway.api.user.model.RegistrationRequest;
import ru.developeerz.gateway.api.user.model.VerificationRequest;

@Service
@RequiredArgsConstructor
public class UserService {

    private final WebClient userWebClient;

    public void registrationUser(RegistrationRequest request) {
        userWebClient.post()
                .uri("/api/user/registration")
                .bodyValue(request)
                .retrieve()
                .bodyToMono(Void.class);
    }

    public Mono<ResponseEntity<JwtResponse>> verificationUser(VerificationRequest request) {
        return userWebClient.post()
                .uri("/api/user/verify")
                .bodyValue(request)
                .retrieve()
                .bodyToMono(JwtResponse.class)
                .map(jwtResponse -> ResponseEntity.ok(jwtResponse));
    }

    public Mono<ResponseEntity<JwtResponse>> loginUser(LoginRequest request) {
        return userWebClient.post()
                .uri("/api/user/login")
                .bodyValue(request)
                .retrieve()
                .bodyToMono(JwtResponse.class)
                .map(jwtResponse -> ResponseEntity.ok(jwtResponse));
    }
}
