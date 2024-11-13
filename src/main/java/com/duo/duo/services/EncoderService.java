package com.duo.duo.services;

public interface EncoderService {
    public String encode(String password);                                  //Codificar uma senha
    public Boolean validate(String password, String encrypt_password);      //Validar se a senha passada pelo usuário coincide com a senha criptografada no banco
}
