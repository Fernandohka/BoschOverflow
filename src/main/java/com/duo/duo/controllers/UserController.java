package com.duo.duo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.duo.duo.dto.Response;
import com.duo.duo.model.User;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.duo.duo.services.UserService;
import com.duo.duo.dto.UserDto.NewUserDto;



@RestController
public class UserController {

    @Autowired
    UserService<User> userService;
    
    @PostMapping("/user/register")
    public ResponseEntity<User> createUser(@RequestBody NewUserDto newUseData) {
        
        return userService.CreateUser(newUseData);
    }
    
}
