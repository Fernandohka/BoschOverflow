package com.duo.duo.services.implementations;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.duo.duo.services.EncoderService;

public class EncoderImplementation implements EncoderService {
    @Override
    public String encode(String password)
    {
        var encoder = new BCryptPasswordEncoder();
        return encoder.encode(password);
    }
    @Override
    public Boolean validate(String password, String encrypt_password) {
        return new BCryptPasswordEncoder().matches(password, encrypt_password);
    }
}