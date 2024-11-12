package com.duo.duo.services;

import com.duo.duo.model.User;

public interface UserAuthService {
    public User login(String login);                    //Método para logar com email, edv, ou nome
}
