package com.duo.duo.services;

import com.duo.duo.dto.LoginDto.LoginDto;
import com.duo.duo.dto.LoginDto.ResponseLoginDto;

public interface UserAuthService {
    public ResponseLoginDto login(LoginDto loginData);                    //Método para logar com email, edv, ou nome
}
