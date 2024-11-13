package com.duo.duo.services;

import com.duo.duo.model.Space;
import com.duo.duo.model.UserSpace;

public interface SpaceActionsService {
    public void postSpace(Space space);                     //Criar um novo espaço
    public void addUser(UserSpace userSpace);               //Adicionar usuário ao espaço
    public void patchPermission(UserSpace userSpace);       //Alterar o nível de permissão de um usuário do espaço
    public void deleteSpace(Space space);                   //Apagar espaço
}
