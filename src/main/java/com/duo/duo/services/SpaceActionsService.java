package com.duo.duo.services;

import org.springframework.http.ResponseEntity;

import com.duo.duo.model.Space;
import com.duo.duo.model.UserSpace;

public interface SpaceActionsService<T> {
    public ResponseEntity<T> postSpace(Space space);                     //Criar um novo espaço
    public ResponseEntity<T> addUser(UserSpace userSpace);               //Adicionar usuário ao espaço
    public ResponseEntity<T> patchPermission(UserSpace userSpace);       //Alterar o nível de permissão de um usuário do espaço
    public ResponseEntity<T> deleteSpace(Space space);                   //Apagar espaço
}
