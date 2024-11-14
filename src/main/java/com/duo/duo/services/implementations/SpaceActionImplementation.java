package com.duo.duo.services.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.duo.duo.dto.Response;
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
    public ResponseEntity<Response<UserSpace>> patchPermission(ChangePermission userSpace) {

        UserSpace permission = permissionRepo.findByUserId(userSpace.userId());
        
        User foundUser = userRepo.findById(userSpace.userId()).get();

        if (permission == null) {
            return new ResponseEntity<>( new Response<UserSpace>(null, "User not found!"), HttpStatus.NOT_FOUND); // Usuário não encontrado, erro 404
        }

        permission.setPermissionLevel(2); //? USUÁRIO MEMBRO - NÍVEL DE PERMISSÃO (2) */
        permission.setUser(foundUser);

        permissionRepo.save(permission);

        return new ResponseEntity<>(new Response<>(permission, "User added to the space successfully!"), HttpStatus.OK); // Usuário adicionado ao Space com sucesso, status 200
    }

    @Override
    public ResponseEntity<Response<Space>> deleteSpace(Space space) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteSpace'");
    }



}
