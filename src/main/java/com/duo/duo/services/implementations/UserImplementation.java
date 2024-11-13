package com.duo.duo.services.implementations;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.duo.duo.dto.Response;
import com.duo.duo.dto.Token;
import com.duo.duo.dto.UserDto.LoginDto;
import com.duo.duo.dto.UserDto.NewUserDto;
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
    public ResponseEntity<Response<User>> CreateUser(NewUserDto newUserData) {

        List<User> users = userRepo.findByName(newUserData.name());

        if (!users.isEmpty()) {
            return new ResponseEntity<>(new Response<>(null, "Já existe um usuário cadastrado com esse nome!"), HttpStatus.UNPROCESSABLE_ENTITY);
        }

        users = userRepo.findByMail(newUserData.mail());

        if (!users.isEmpty()) {
            return new ResponseEntity<>(new Response<>(null, "Já existe um usuário cadastrado com esse E-mail"), HttpStatus.UNPROCESSABLE_ENTITY);
        }

        users = userRepo.findByEdv(newUserData.edv());

        if (!users.isEmpty()) {
            return new ResponseEntity<>(new Response<>(null, "Já existe um usuário cadastrado com esse EDV"), HttpStatus.UNPROCESSABLE_ENTITY);
        }

        User newUser = new User();

        newUser.setEdv(newUserData.edv());
        newUser.setName(newUserData.name());
        newUser.setMail(newUserData.mail());
        newUser.setPassword(encoder.encode(newUserData.password()));

        userRepo.save(newUser);

        return new ResponseEntity<>(new Response<>(newUser, "Usuário criado com sucesso!"), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Response<Token>> Login(LoginDto loginData) {

        List<User> users = userRepo.loginMailOrNameOrEDV(loginData.login());

        if (users.isEmpty()) {
            return new ResponseEntity<>(new Response<>(null, "Usuário não foi encontrado!"), HttpStatus.BAD_REQUEST);
        }

        User user = users.get(0);

        if (!encoder.validate(loginData.password(), user.getPassword())) {
            return new ResponseEntity<>(new Response<>(null, "Senha incorreta!"), HttpStatus.BAD_REQUEST);
        }

        Token token = new Token();
        token.setId(user.getId());

        String jwt = jwtService.get(token);

        // ! Arrumar depois, há algum erro na hora de enviar a string o jwt
        // return new ResponseEntity<>(new Response<>(jwt, "Senha incorreta!"), HttpStatus.OK);
        
        return null;
    }
}
