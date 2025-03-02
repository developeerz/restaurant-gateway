package ru.developeerz.gateway.api.user.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@NoArgsConstructor
public class VerificationRequest {

    @NotBlank
    private String email;

    @NotNull
    private Integer verificationCode;
}
