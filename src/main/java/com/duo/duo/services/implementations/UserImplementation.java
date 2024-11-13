package com.duo.duo.services.implementations;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.duo.duo.dto.Response;
import com.duo.duo.dto.UserDto.NewUserDto;
import com.duo.duo.model.User;
import com.duo.duo.repositories.UserRepository;
import com.duo.duo.services.EncoderService;
import com.duo.duo.services.UserService;

public class UserImplementation implements UserService {

    @Autowired
    UserRepository userRepo;

    @Autowired
    EncoderService encoder;

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
    public ResponseEntity<Response<User>> Login() {
        return null;
    }
    
}
