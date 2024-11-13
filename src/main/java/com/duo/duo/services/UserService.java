package com.duo.duo.services;

import com.duo.duo.model.User;

public interface UserService {
    public void CreateUser(User user);              //Criar usuário
    public void DeleteUser(User user);              //Deletar definitivamente um usuário do banco
}
