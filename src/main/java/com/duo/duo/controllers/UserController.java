package com.duo.duo.controllers;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.duo.duo.dto.UserDto.LoginDto;
import com.duo.duo.dto.UserDto.NewUserDto;
import com.duo.duo.dto.UserDto.ResponseNewUserDto;
import com.duo.duo.model.User;
import com.duo.duo.services.UserService;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/user/register")
    public ResponseEntity<ResponseNewUserDto> createUser(@RequestBody NewUserDto newUserData) {
        
        userService.CreateUser(newUserData);

        ResponseNewUserDto response = userService.checkFields(newUserData);
        ArrayList<String> errors = response.messages();

        if (!response.messages().isEmpty()) {
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        User user = userService.CreateUser(newUserData);

        response = new ResponseNewUserDto(user, errors);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/user/login")
    public String postMethodName(@RequestBody LoginDto loginData) {
        
        userService.Login(loginData);

        return null;
    }
    
}
