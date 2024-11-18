package com.duo.duo.services.implementations;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.duo.duo.dto.UserDto.NewUserDto;
import com.duo.duo.dto.UserDto.ResponseNewUserDto;
import com.duo.duo.model.User;
import com.duo.duo.repositories.UserRepository;
import com.duo.duo.services.EncoderService;
import com.duo.duo.services.UserService;

public class UserImplementation implements UserService {

    @Autowired
    UserRepository userRepo;

    @Autowired
    EncoderService encoder;

    // * Função padrão de usuário criado. Essa função só irá rodar no Controller caso a função CheckFields não retorne erro.
    // * Aqui a senha é decodificada

    @Override
    public User CreateUser(NewUserDto newUserData) {

        User newUser = new User();

        newUser.setEdv(newUserData.edv());
        newUser.setName(newUserData.name());
        newUser.setMail(newUserData.mail());
        newUser.setPassword(encoder.encode(newUserData.password()));

        userRepo.save(newUser);

        return newUser;
    }

    /*
     * Verificação dos campos e de usuário ja cadastrado com os dados informados
     * Note que a lista "users" é atualizada sempre, para podermos retornar diferentes tipos de erros
     * Caso não tenha nenhum desses erros, retornamos a nossa lista vazia, que é verificada no controller
     * Caso a lista seja vazia, o usuário é criado 
    */

    @Override
    public ResponseNewUserDto checkFields(NewUserDto newUserData) {

        System.out.println(newUserData);

        List<User> users = userRepo.findByName(newUserData.name());

        ArrayList<String> messages =  new ArrayList<>();

        
        if (!verifyFieldsRegister(newUserData)) {
            messages.add("Preencha todos os campos!");
        }
        
        if (!users.isEmpty()) {
            messages.add("Já existe um usuário cadastrado com este nome!");
        }
        
        users = userRepo.findByMail(newUserData.mail());
        
        if (!users.isEmpty()) {
            messages.add("Já existe um usuário cadastrado com este e-mail!");
        }
        
        users = userRepo.findByEdv(newUserData.edv());
        
        if (!users.isEmpty()) {
            messages.add("Já existe um usuário cadastrado com este EDV!");
        }
        
        ResponseNewUserDto response = new ResponseNewUserDto(null, messages);
        
        return response;
    }

    /*
     * Funções que verificam campos vazios 
     * As funções foram feitas para reduzir o corpo do código de Login e Registro.
     * É feita a verificação tanto de NULL quanto de "" para ambos os casos serem barrados.
    */

    public Boolean verifyFieldsRegister(NewUserDto newUserData) {
        if (newUserData.password() == null || newUserData.name() == null || newUserData.mail() == null) {
            return false;
        }
        
        return !newUserData.password().equals("") || newUserData.name().equals("") || newUserData.mail().equals("");
    }
}
