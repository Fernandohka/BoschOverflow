package com.duo.duo.services;

import java.util.ArrayList;

import com.duo.duo.dto.space.AddUserToSpace;
import com.duo.duo.dto.space.ChangePermission;
import com.duo.duo.dto.space.SpaceCreation;
import com.duo.duo.model.Space;
import com.duo.duo.model.UserSpace;

public interface SpaceActionsService {
    public Boolean checkSpaceNameForPost(String name);                  //Criar um novo espaço
    public Space postSpace(SpaceCreation space);   

    public Boolean checkForSpace(Long id);                              //checa se existe um space
    public Boolean checkForUser(Long id);                               //checa se existe um user
    public UserSpace addUser(AddUserToSpace userSpace);                 // cria uma permissão
              
    public UserSpace patchPermission(ChangePermission userSpace);       //Alterar o nível de permissão de um usuário do espaço
    public Space deleteSpace(Space space);                              //Apagar espaço

    public ArrayList<Space> getSpaces(String name, Integer page, Integer limit);
}
