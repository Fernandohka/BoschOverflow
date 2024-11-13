package com.duo.duo.services;

import org.springframework.http.ResponseEntity;

import com.duo.duo.dto.UserDto.NewUserDto;

public interface UserService<T> {
    public ResponseEntity<T> CreateUser(NewUserDto newUserData);              //Criar usuário     
    public ResponseEntity<T> Login();                                        //Deletar definitivamente um usuário do banco
}
