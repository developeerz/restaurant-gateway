package ru.developeerz.gateway.core.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import ru.developeerz.gateway.api.user.model.LoginRequest;
import ru.developeerz.gateway.api.user.model.JwtResponse;
import ru.developeerz.gateway.api.user.model.RegistrationRequest;
import ru.developeerz.gateway.api.user.model.VerificationRequest;

@Service
@RequiredArgsConstructor
public class UserService {

    private final WebClient userWebClient;

    public ResponseEntity<?> registrationUser(RegistrationRequest request) {
          ResponseEntity<?> response = userWebClient.post()
                .uri("/user/registration")
                .bodyValue(request)
                .retrieve()
                .bodyToMono(ResponseEntity.class)
                .block();

        if (response == null || response.getStatusCode().is5xxServerError()) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Сервис упал...");
        } else if (response.getStatusCode().is4xxClientError()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Неверные данные");
        }

        return ResponseEntity.ok("Verify your email");
    }

    public ResponseEntity<?> verificationUser(VerificationRequest request) {
        ResponseEntity<JwtResponse> response = userWebClient.post()
                .uri("/user/verify")
                .bodyValue(request)
                .retrieve()
                .toEntity(JwtResponse.class)
                .block();

        if (response == null || response.getStatusCode().is5xxServerError()) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Сервис упал...");
        } else if (response.getStatusCode().is4xxClientError()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Неверные данные");
        }

        return response;
    }

    public ResponseEntity<?> loginUser(LoginRequest request) {
        ResponseEntity<JwtResponse> response = userWebClient.post()
                .uri("/user/login")
                .bodyValue(request)
                .retrieve()
                .toEntity(JwtResponse.class)
                .block();

        if (response == null || response.getStatusCode().is5xxServerError()) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Сервис упал...");
        } else if (response.getStatusCode().is4xxClientError()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Неверные данные");
        }

        return response;
    }
}
