package com.duo.duo.services;

public interface UserValidateService {
    public Boolean validatePassword(String password);   //Valida se a senha atende os padrões
    public Boolean validateEmail(String email);         //valida se o email é válido
    public Boolean validateEdv(String edv);             //valida se o edv inserido atende o formato (o tamanho talvez, e se todos os caracteres sao numeros)
    public Boolean isEmailFree(String email);           //valida se o email está disponível ou não
    public Boolean isEdvFree(String edv);               //valida se o edv está disponível ou não
}
