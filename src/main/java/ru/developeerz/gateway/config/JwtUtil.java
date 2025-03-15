package ru.developeerz.gateway.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class JwtUtil {

    private final SecretKey key;

    public JwtUtil(@Value("${jwt.secret.access}") String secretKey) {
        key = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
    }

    public Claims getClaims(String jwtToken) {
        return Jwts.parser().verifyWith(key).build().parseSignedClaims(jwtToken).getPayload();
    }

    public Set<Authority> getAuthorities(Claims claims) {
        Set<String> authorities = claims.get("roles", Set.class);
        return authorities.stream().map(Authority::valueOf).collect(Collectors.toSet());
    }

    public  int getPrincipal(Claims claims) {
        return claims.get("id", Integer.class);
    }
}
