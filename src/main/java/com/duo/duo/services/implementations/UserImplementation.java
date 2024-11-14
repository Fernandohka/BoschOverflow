package com.duo.duo.services.implementations;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.duo.duo.dto.Token;
import com.duo.duo.dto.UserDto.LoginDto;
import com.duo.duo.dto.UserDto.NewUserDto;
import com.duo.duo.dto.UserDto.ResponseLoginDto;
import com.duo.duo.dto.UserDto.ResponseNewUserDto;
import com.duo.duo.model.User;
import com.duo.duo.repositories.UserRepository;
import com.duo.duo.services.EncoderService;
import com.duo.duo.services.JwtService;
import com.duo.duo.services.UserService;

public class UserImplementation implements UserService {

    @Autowired
    UserRepository userRepo;

    @Autowired
    EncoderService encoder;

    @Autowired
    JwtService<Token> jwtService;

    @Override
    public User CreateUser(NewUserDto newUserData) {

        User newUser = new User();

        newUser.setEdv(newUserData.edv());
        newUser.setName(newUserData.name());
        newUser.setMail(newUserData.mail());
        newUser.setPassword(encoder.encode(newUserData.password()));

        userRepo.save(newUser);

        return newUser;
    }

    @Override
    public ResponseLoginDto Login(LoginDto loginData) {

        List<User> users = userRepo.loginMailOrNameOrEDV(loginData.login());

        ArrayList<String> messages = new ArrayList<>();

        if (users.isEmpty()) {
            messages.add("Usuário não encontrado!");
            return new ResponseLoginDto(null, messages);
        }
        
        User user = users.get(0);

        if (encoder.validate(loginData.password(), user.getPassword())) {
            messages.add("Senha inválida!");
            return new ResponseLoginDto(null, messages);
        }

        Token token = new Token();
        token.setId(user.getId());

        var jwt = jwtService.get(token);

        messages.add("Usuário logado com sucesso!");
        return new ResponseLoginDto(jwt, messages);
    }

    @Override
    public ResponseNewUserDto checkFields(NewUserDto newUserData) {

        List<User> users = userRepo.findByName(newUserData.name());

        ArrayList<String> messages =  new ArrayList<>();

        
        if (newUserData.password() == null || newUserData.name() == null || newUserData.mail() == null) {
            messages.add("Preencha todos os campos!");
        }
        
        if (!users.isEmpty()) {
            messages.add("Já existe um usuário cadastrado com este nome!");
        }
        
        users = userRepo.findByMail(newUserData.mail());
        
        if (!users.isEmpty()) {
            messages.add("Já existe um usuário cadastrado com este e-mail!");
        }
        
        users = userRepo.findByEdv(newUserData.edv());
        
        if (!users.isEmpty()) {
            messages.add("Já existe um usuário cadastrado com este EDV!");
        }
        
        ResponseNewUserDto response = new ResponseNewUserDto(null, messages);
        
        return response;
    }
}
