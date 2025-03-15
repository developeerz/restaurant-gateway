package ru.developeerz.gateway.api.user;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import ru.developeerz.gateway.api.ApiPaths;
import ru.developeerz.gateway.api.user.model.JwtResponse;
import ru.developeerz.gateway.api.user.model.LoginRequest;
import ru.developeerz.gateway.api.user.model.RegistrationRequest;
import ru.developeerz.gateway.api.user.model.VerificationRequest;
import ru.developeerz.gateway.core.service.UserService;


@Tag(name = "Контроллер пользователей")
@RestController
@RequiredArgsConstructor
@Validated
public class UserController {

    private final UserService userService;

    @Operation(summary = "Регистрация")
    @PostMapping(ApiPaths.REGISTRATION)
    public void userRegistration(@RequestBody @Valid RegistrationRequest request) {
        userService.registrationUser(request);
    }

    @PostMapping(ApiPaths.VERIFY)
    public Mono<ResponseEntity<JwtResponse>> userVerify(@RequestBody @Valid VerificationRequest request) {
        return userService.verificationUser(request);
    }

    @PostMapping(ApiPaths.LOGIN)
    public Mono<ResponseEntity<JwtResponse>> userVerify(@RequestBody @Valid LoginRequest request) {
        return userService.loginUser(request);
    }
}
