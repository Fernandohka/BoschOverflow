package com.duo.duo.services.implementations;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.duo.duo.dto.Token;
import com.duo.duo.dto.LoginDto.LoginDto;
import com.duo.duo.dto.LoginDto.ResponseLoginDto;
import com.duo.duo.model.User;
import com.duo.duo.repositories.UserRepository;
import com.duo.duo.services.EncoderService;
import com.duo.duo.services.JwtService;
import com.duo.duo.services.UserAuthService;

public class UserAuthImplementation implements UserAuthService {

    @Autowired
    UserRepository userRepo;

    @Autowired
    EncoderService encoder;

    @Autowired
    JwtService<Token> jwtService;

    // * Login que pode ser feito tanto com Email, quanto com nome ou EDV. 

    @Override
    public ResponseLoginDto login(LoginDto loginData) {

        List<User> users = userRepo.loginMailOrNameOrEDV(loginData.login());

        ArrayList<String> messages = new ArrayList<>();

        if (!verifyFieldsLogin(loginData)) {
            messages.add("Preencha todos os campos;");
            return new ResponseLoginDto(null, messages);
        }

        if (users.isEmpty()) {
            messages.add("Usuário não encontrado!");
            return new ResponseLoginDto(null, messages);
        }
        
        User user = users.get(0);

        if (!encoder.validate(loginData.password(), user.getPassword())) {
            messages.add("Senha inválida!");
            return new ResponseLoginDto(null, messages);
        }

        Token token = new Token();
        token.setId(user.getId());

        var jwt = jwtService.get(token);

        messages.add("Usuário logado com sucesso!");
        return new ResponseLoginDto(jwt, messages);
    }

    public Boolean verifyFieldsLogin(LoginDto loginData) {
        if (loginData.password() == null || loginData.login() == null) {
            return false;
        }
        
        return !loginData.password().equals("") || loginData.login().equals("");
    }
}

