package com.duo.duo.services;

import org.springframework.http.ResponseEntity;

import com.duo.duo.dto.Response;
import com.duo.duo.dto.space.AddUserToSpace;
import com.duo.duo.dto.space.ChangePermission;
import com.duo.duo.dto.space.SpaceCreation;
import com.duo.duo.model.Space;
import com.duo.duo.model.UserSpace;

public interface SpaceActionsService {
    public Boolean checkSpaceNameForPost(String name);             //Criar um novo espaço
    public Space postSpace(SpaceCreation space);             //Criar um novo espaço
    public ResponseEntity<Response<UserSpace>> addUser(AddUserToSpace userSpace);           //Adicionar usuário ao espaço
    public ResponseEntity<Response<UserSpace>> patchPermission(ChangePermission userSpace);       //Alterar o nível de permissão de um usuário do espaço
    public ResponseEntity<Response<Space>> deleteSpace(Space space);                   //Apagar espaço
}
