package com.duo.duo.services.implementations;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;

import com.duo.duo.dto.space.AddUserToSpace;
import com.duo.duo.dto.space.SpaceCreation;
import com.duo.duo.model.Space;
import com.duo.duo.model.User;
import com.duo.duo.model.UserSpace;
import com.duo.duo.repositories.SpaceRepository;
import com.duo.duo.repositories.UserRepository;
import com.duo.duo.repositories.UserSpaceRepository;
import com.duo.duo.services.SpaceActionsService;


/*
    * Response DTO - record que retorna um objeto genérico T e uma mensagem 
*/
 
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
    public Space postSpace(SpaceCreation space, Long userId) {
        
        Space newSpace = new Space();
        newSpace.setName(space.name());

        var found = userRepo.findById(userId).get();
        
        UserSpace permission = new UserSpace();
        permission.setSpace(newSpace);
        permission.setUser(found);
        permission.setPermissionLevel(3);   // seta o usuário criador como adm
                                                            //? USUÁRIO ADM - NÍVEL DE PERMISSÃO (3) */

        spaceRepo.save(newSpace);
        permissionRepo.save(permission);


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
    public UserSpace manageSpaceUsers(AddUserToSpace userSpace) {

        Optional<UserSpace> found = permissionRepo.findPermission(userSpace.userId(), userSpace.spaceId() ); // tenta achar uma permissão para aquele uusário - caso ache, realiza um PATCH, se não, cria uma permissão nova


        if (found.isEmpty()) {
            UserSpace permission = new UserSpace();

            if (userSpace.permission() < 1 || userSpace.permission() > 3) {
                permission.setPermissionLevel(2); //se o front mandar errado deixa como user default
    
            } else {
                permission.setPermissionLevel(userSpace.permission()); //? USUÁRIO MEMBRO - NÍVEL DE PERMISSÃO (2) */
            }
            
            Space foundSpace = spaceRepo.findById(userSpace.spaceId()).get();
            User foundUser = userRepo.findById(userSpace.userId()).get();
            
            permission.setSpace(foundSpace);
            permission.setUser(foundUser);
    
            permissionRepo.save(permission);
    
            return permission; 

        } else {

            var foundPermission = found.get();
            foundPermission.setPermissionLevel(userSpace.permission());
            permissionRepo.save(foundPermission);

            return foundPermission; 

        }

        
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

    @Override
    public Integer checkUserPermission(Long userId, Long spaceId) {
        
        System.out.println(spaceId);
        System.out.println(userId);
    
        Optional<UserSpace> found = permissionRepo.findPermission(userId, spaceId);
    
        // Safe handling: Check if the Optional is present
        if (found.isEmpty()) {
            System.out.println("No permission found for the given userId and spaceId.");
            return null; // Return null if not found
        }

        UserSpace userSpace = found.get();
    
        return userSpace.getPermissionLevel();
        
    }



}
