package com.duo.duo.services.implementations;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;

import com.duo.duo.dto.space.AddUserToSpace;
import com.duo.duo.dto.space.ChangePermission;
import com.duo.duo.dto.space.SpaceCreation;
import com.duo.duo.model.Space;
import com.duo.duo.model.User;
import com.duo.duo.model.UserSpace;
import com.duo.duo.repositories.SpaceRepository;
import com.duo.duo.repositories.UserRepository;
import com.duo.duo.repositories.UserSpaceRepository;
import com.duo.duo.services.SpaceActionsService;


//* Response DTO - record que retorna um objeto genérico T e uma mensagem */
 
public class SpaceActionImplementation implements SpaceActionsService{

    @Autowired
    SpaceRepository spaceRepo;

    @Autowired
    UserSpaceRepository permissionRepo;

    @Autowired
    UserRepository userRepo;

    @Override
    public Boolean checkSpaceNameForPost(String name) {
        Space found = spaceRepo.findByName(name);

        return found != null; // se encontrsr, retorna falso por não pode ser usaod;
    }

    @Override
    public Space postSpace(SpaceCreation space) {
        
        Space newSpace = new Space();
        newSpace.setName(space.name());
        spaceRepo.save(newSpace);

        return newSpace;
    }

    @Override
    public Boolean checkForSpace(Long id) {
        Space found = spaceRepo.findById(id).get();
        return found != null;
    }

    @Override
    public Boolean checkForUser(Long id) {
        User found = userRepo.findById(id).get();
        return found != null;
    }
    
    @Override
    public UserSpace addUser(AddUserToSpace userSpace) {
        UserSpace permission = new UserSpace();

        permission.setPermissionLevel(2); //? USUÁRIO MEMBRO - NÍVEL DE PERMISSÃO (2) */
        
        Space found = spaceRepo.findById(userSpace.spaceId()).get();
        User foundUser = userRepo.findById(userSpace.spaceId()).get();
        
        permission.setSpace(found);
        permission.setUser(foundUser);

        permissionRepo.save(permission);

        return permission; // Usuário adicionado ao Space com sucesso
    }


    @Override
    public UserSpace patchPermission(ChangePermission userSpace) {

        //* ROTA NÃO ESPECIFICADA NO QUADRO - PRIORIDADE MENOR */

        throw null;
    }

    @Override
    public Space deleteSpace(Space space) {

        spaceRepo.delete(space);

        return space;
    }

    @Override
    public ArrayList<Space> getSpaces(String name, Integer page, Integer limit) {
        var results = spaceRepo.findByNameContains(name, PageRequest.of(page, limit)); // ai que medo
        
        return new ArrayList<>(results);
    }



}
