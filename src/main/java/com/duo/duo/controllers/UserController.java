package com.duo.duo.controllers;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.duo.duo.dto.LoginDto.LoginDto;
import com.duo.duo.dto.LoginDto.ResponseLoginDto;
import com.duo.duo.dto.UserDto.NewUserDto;
import com.duo.duo.dto.UserDto.ResponseNewUserDto;
import com.duo.duo.model.User;
import com.duo.duo.services.UserService;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/user")
    public ResponseEntity<ResponseNewUserDto> createUser(@RequestBody NewUserDto newUserData) {

        ResponseNewUserDto response = userService.checkFields(newUserData);
        ArrayList<String> errors = response.messages();

        if (!response.messages().isEmpty()) {
            return new ResponseEntity<>(response, HttpStatus.CONFLICT);
        }

        User user = userService.CreateUser(newUserData);

        response = new ResponseNewUserDto(user, errors);
        
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/auth")
    public ResponseEntity<ResponseLoginDto> login(@RequestBody LoginDto loginData) {
        
        ResponseLoginDto response = userService.Login(loginData);

        if (response.token() == null) {
            System.out.println(loginData);
            return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED); // * Erro Http 401
        }

        System.out.println(loginData);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    
}
