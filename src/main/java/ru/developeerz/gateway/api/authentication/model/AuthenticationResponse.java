package ru.developeerz.gateway.api.authentication.model;

import java.util.Set;

public record AuthenticationResponse(

        long userId,

        Set<String> authority
) {

}
