package com.duo.duo.services;

import org.springframework.http.ResponseEntity;

import com.duo.duo.dto.UserDto.NewUserDto;
import com.duo.duo.model.User;

public interface UserService {
    public ResponseEntity<User> CreateUser(NewUserDto newUserData);              //Criar usuário
    public void DeleteUser(User user);                                       //Deletar definitivamente um usuário do banco
}
