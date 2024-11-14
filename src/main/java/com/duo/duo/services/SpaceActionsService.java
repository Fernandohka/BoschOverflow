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
    public Space postSpace(SpaceCreation space);   

    public Boolean checkForSpace(Long id);          //checa se existe um space
    public Boolean checkForUser(Long id);          //checa se existe um user
    public UserSpace addUser(AddUserToSpace userSpace); // cria uma permissão
              
    public ResponseEntity<Response<UserSpace>> patchPermission(ChangePermission userSpace);       //Alterar o nível de permissão de um usuário do espaço
    public ResponseEntity<Response<Space>> deleteSpace(Space space);                   //Apagar espaço
}
