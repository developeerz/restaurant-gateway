package ru.developeerz.gateway.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import ru.developeerz.gateway.api.ApiPaths;

import java.util.List;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtFilter jwtFilter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .httpBasic(AbstractHttpConfigurer::disable)
                .csrf(AbstractHttpConfigurer::disable)
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(ApiPaths.LOGIN, ApiPaths.REGISTRATION, ApiPaths.VERIFY).permitAll()
                        .requestMatchers(HttpMethod.GET, ApiPaths.MENU).permitAll()
                        .requestMatchers(HttpMethod.DELETE, ApiPaths.MENU).hasAuthority(Authority.ADMIN.getAuthority())
                        .requestMatchers(HttpMethod.POST, ApiPaths.MENU).hasAuthority(Authority.ADMIN.getAuthority())
                        .requestMatchers(HttpMethod.GET, ApiPaths.BOOKING).permitAll()
                        .requestMatchers(HttpMethod.POST, ApiPaths.BOOKING).hasAnyAuthority(
                                Authority.ADMIN.getAuthority(), Authority.USER.getAuthority()
                        )
                        .requestMatchers(HttpMethod.DELETE, ApiPaths.BOOKING).hasAnyAuthority(
                                Authority.ADMIN.getAuthority(), Authority.USER.getAuthority()
                        )
                        .anyRequest().denyAll()
                )
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();

        configuration.setAllowedOriginPatterns(List.of("*"));

        configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "PATCH"));

        configuration.setAllowedHeaders(List.of("Authorization", "Content-Type"));

        configuration.setAllowCredentials(true);

        configuration.setMaxAge(3600L);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);

        return source;
    }
}
