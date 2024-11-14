package com.duo.duo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.duo.duo.dto.Token;
import com.duo.duo.filters.JWTAuthenticationFilter;
import com.duo.duo.services.JwtService;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Autowired
    JwtService<Token> jwtService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(authorize -> authorize
                .requestMatchers("/user/login").permitAll() // permite apenas a rota de login
                .anyRequest().authenticated()                           // todas as outras requerem autenticação                                                                 
                                                                                                         
            )
            .addFilterBefore(new JWTAuthenticationFilter(jwtService), UsernamePasswordAuthenticationFilter.class)
            .build();
    }

}