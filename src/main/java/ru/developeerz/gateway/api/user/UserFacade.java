package ru.developeerz.gateway.api.user;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;
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
public class UserFacade {

    private final WebClient userWebClient;

    public Mono<ResponseEntity<Void>> registrationUser(RegistrationRequest request) {
        return userWebClient.post()
                .uri("/api/user/registration")
                .bodyValue(request)
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, clientResponse ->
                        clientResponse.createException().flatMap(Mono::error))
                .toBodilessEntity();
    }

    public Mono<JwtResponse> verificationUser(VerificationRequest request) {
        return userWebClient.post()
                .uri("/api/user/verify")
                .bodyValue(request)
                .retrieve()
                .bodyToMono(JwtResponse.class);
    }

    public Mono<JwtResponse> loginUser(LoginRequest request) {
        return userWebClient.post()
                .uri("/api/user/login")
                .bodyValue(request)
                .retrieve()
                .bodyToMono(JwtResponse.class);
    }

}
