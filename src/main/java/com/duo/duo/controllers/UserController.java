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
import com.duo.duo.services.UserAuthService;
import com.duo.duo.services.UserService;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    UserAuthService authService;

    @PostMapping("/user")
    public ResponseEntity<ResponseNewUserDto> createUser(@RequestBody NewUserDto newUserData) {

        /*
            * Aqui temos a função que verifica se os campos estão nulos ou vazios!
            * Abaixo da verificação dos campos, há a criação da lista "erros", que será utilizada no retorno dos dados ao frontEnd.
        */ 

        ResponseNewUserDto response = userService.checkFields(newUserData);
        ArrayList<String> errors = response.messages();

        if (!response.messages().isEmpty()) {
            return new ResponseEntity<>(response, HttpStatus.CONFLICT);
        }

        User user = userService.CreateUser(newUserData);

        response = new ResponseNewUserDto(user, errors);
        
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /*
     * Login do usuário 
    */

    @PostMapping("/auth")
    public ResponseEntity<ResponseLoginDto> login(@RequestBody LoginDto loginData) {
        
        ResponseLoginDto response = authService.login(loginData);

        if (response.token() == null) {
            System.out.println(loginData);
            return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED); // * Erro Http 401
        }

        System.out.println(loginData);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    
}
