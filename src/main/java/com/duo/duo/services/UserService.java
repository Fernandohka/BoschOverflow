package com.duo.duo.services;

import com.duo.duo.dto.LoginDto.LoginDto;
import com.duo.duo.dto.LoginDto.ResponseLoginDto;
import com.duo.duo.dto.UserDto.NewUserDto;
import com.duo.duo.dto.UserDto.ResponseNewUserDto;
import com.duo.duo.model.User;

public interface UserService {
    public User CreateUser(NewUserDto newUserData);
    public ResponseLoginDto Login(LoginDto loginData);
    public ResponseNewUserDto checkFields(NewUserDto newUserData);             
}
