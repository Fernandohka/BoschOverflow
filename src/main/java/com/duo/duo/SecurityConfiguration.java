package com.duo.duo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

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
                .requestMatchers("/user").permitAll()
                .requestMatchers("/auth").permitAll()
                .anyRequest().authenticated()                                                                         
                                                                                                         
            )
            .addFilterBefore(new JWTAuthenticationFilter(jwtService), UsernamePasswordAuthenticationFilter.class)
            .cors(config -> {
                CorsConfiguration configuration = new CorsConfiguration();
                configuration.addAllowedOrigin("http://localhost:5500");
                configuration.addAllowedOrigin("http://127.0.0.1:5500");
                configuration.addAllowedHeader("Authorization");
                configuration.addAllowedHeader("Content-Type");
                configuration.addAllowedMethod("GET");
                configuration.addAllowedMethod("POST");
                configuration.addAllowedMethod("PUT");
                configuration.addAllowedMethod("DELETE");
                configuration.setAllowCredentials(true);
        
                // Registro da configuração CORS
                UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
                source.registerCorsConfiguration("/**", configuration);
                config.configurationSource(source);
            })
            .build();
    }

}