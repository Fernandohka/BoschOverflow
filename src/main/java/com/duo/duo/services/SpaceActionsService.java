package com.duo.duo.services;

import java.util.ArrayList;

import com.duo.duo.dto.space.AddUserToSpace;
import com.duo.duo.dto.space.SpaceCreation;
import com.duo.duo.model.Space;
import com.duo.duo.model.UserSpace;

public interface SpaceActionsService {
    public Boolean checkSpaceNameForPost(String name);                  //Criar um novo espaço
    public Space postSpace(SpaceCreation space, Long userId);   

    public Boolean checkForSpace(Long id);                              //checa se existe um space
    public Boolean checkForUser(Long id);                               //checa se existe um user
    public UserSpace manageSpaceUsers(AddUserToSpace userSpace);        // cria uma permissão - gerencia usuários dentro de um espaço

    public Integer checkUserPermission(Long userId, Long spaceId);                        // retorn o tipo de usuario
              
    public Space deleteSpace(Space space);                              //Apagar espaço

    public ArrayList<Space> getSpaces(String name, Integer page, Integer limit);
}
