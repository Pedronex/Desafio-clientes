package com.nexdev.jaimedesafio.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.OrRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtTokenFilter jwtTokenFilter;
    private final AuthenticationProvider authenticationProvider;

    // URLs públicas, que não exigem autenticação
    final RequestMatcher PUBLIC_URLS = new OrRequestMatcher(
            new AntPathRequestMatcher("/login"),
            new AntPathRequestMatcher("/user")
    );

    // URLs privadas, que exigem autenticação
    final RequestMatcher PRIVATE_URLS = new OrRequestMatcher(
            new AntPathRequestMatcher("/client", "POST"),
            new AntPathRequestMatcher("/clients", "GET"),
            new AntPathRequestMatcher("/client", "PUT"),
            new AntPathRequestMatcher("/client", "DELETE")
    );

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // Desabilitar o CSRF (Cross-Site Request Forgery)
        http.csrf(AbstractHttpConfigurer::disable);
        // Configurar o gerenciamento de sessões para ser sem estado (STATELESS)
        http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        http.authorizeHttpRequests(auth -> auth.requestMatchers(PUBLIC_URLS).permitAll()
                        .requestMatchers(new AntPathRequestMatcher("/h2-console/**")).permitAll() // Permitir acesso ao console H2
                        .requestMatchers(PRIVATE_URLS).authenticated() // Exigir autenticação para URLs privadas
                        .anyRequest().permitAll()) // Permitir acesso a outras URLs
                .httpBasic(Customizer.withDefaults()); // Configuração básica de autenticação HTTP
        http.authenticationProvider(authenticationProvider);
        http.addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public WebSecurityCustomizer webSecurityConfigurer() {
        // Ignorar requisições para o console H2
        return (web) -> web.ignoring().requestMatchers(new AntPathRequestMatcher("/h2-console/**"));
    }

}
