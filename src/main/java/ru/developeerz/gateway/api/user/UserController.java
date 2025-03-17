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


@Tag(name = "Контроллер пользователей")
@RestController
@RequiredArgsConstructor
@Validated
public class UserController {

    private final UserFacade userFacade;

    @Operation(summary = "Регистрация")
    @PostMapping(ApiPaths.REGISTRATION)
    public Mono<ResponseEntity<Void>> userRegistration(@RequestBody @Valid RegistrationRequest request) {
        return userFacade.registrationUser(request);
    }

    @PostMapping(ApiPaths.VERIFY)
    public Mono<JwtResponse> userVerify(@RequestBody @Valid VerificationRequest request) {
        return userFacade.verificationUser(request);
    }

    @PostMapping(ApiPaths.LOGIN)
    public Mono<JwtResponse> userVerify(@RequestBody @Valid LoginRequest request) {
        return userFacade.loginUser(request);
    }

}
