package ru.developeerz.gateway.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import ru.developeerz.gateway.api.authentication.AuthenticationFacade;
import ru.developeerz.gateway.api.authentication.model.AuthenticationRequest;
import ru.developeerz.gateway.api.authentication.model.AuthenticationResponse;

import java.io.IOException;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    private final AuthenticationFacade authenticationFacade;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {

        String jwtToken = getJwtToken(request);
        if (jwtToken == null) {
            filterChain.doFilter(request, response);
            return;
        }

        AuthenticationResponse auth = authenticationFacade.getAuthentication(new AuthenticationRequest(jwtToken));
        if (auth != null) {
            Authentication authentication = new UsernamePasswordAuthenticationToken(
                    auth.userId(), null, extractAuthority(auth.authority())
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        filterChain.doFilter(request, response);
    }

    private String getJwtToken(HttpServletRequest request) {
        String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return null;
        }

        return authHeader.substring(7);
    }

    private Set<Authority> extractAuthority(Set<String> auths) {
        return auths.stream().map(this::authorityCheck).filter(Objects::nonNull).collect(Collectors.toSet());
    }

    private Authority authorityCheck(String authority) {
        try {
            return Authority.valueOf(authority);
        } catch (IllegalArgumentException e) {
            return null;
        }
    }
}
