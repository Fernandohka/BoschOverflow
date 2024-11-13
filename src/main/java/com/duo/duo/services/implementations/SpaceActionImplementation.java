package com.duo.duo.services.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.duo.duo.dto.Response;
import com.duo.duo.dto.space.AddUserToSpace;
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

    //* recebe uma DTO SpaceCreation (String name) e retorna um Response do tipo <Space> com o Space criado e uma mensagem*/

    public ResponseEntity<Response<Space>> postSpace(SpaceCreation space) {
        var found = spaceRepo.findByName(space.name());

        if (found != null) {
            /* caso já exista um space com esse nome, retorna um objeto vazio e uma mensagem de erro */
            return new ResponseEntity<>( new Response<Space>(null, "There is already a space with that name. Choose another!"), HttpStatus.UNPROCESSABLE_ENTITY); // Não se pode criar um espaço com nome já existente, status 422
        }

        Space newSpace = new Space();
        newSpace.setName(space.name());
        spaceRepo.save(newSpace);

        return new ResponseEntity<>( new Response<Space>(newSpace, "Space created successfully!"), HttpStatus.CREATED); // Space criado com sucesso, status 201

    }

    @Override

    /*
        * recebe uma AddUserToSpace DTO que contém:
            * 1. id do usuário a ser adicionado
            * 2. id do space a receber esse novo usuário

        * e retorna um Response do tipo <UserSpace> com a permissão criada e uma mensagem
        
        ? VERIFICAR A MODEL DE "UserSpace" PARA CONFERIR OS NÍVEIS DE PERMISSÃO - USUÁRIO MEMBRO : NÍVEL DE PERMISSÃO (2)
     */
    
    public ResponseEntity<Response<UserSpace>> addUser(AddUserToSpace userSpace) {

        Space found = spaceRepo.findById(userSpace.spaceId()).get();

        if (found == null) {
            return new ResponseEntity<>( new Response<UserSpace>(null, "Space not found!"), HttpStatus.NOT_FOUND); // Space não encontrado, erro 404
        }

        User foundUser = userRepo.findById(userSpace.userId()).get();

        if (foundUser == null) {
            return new ResponseEntity<>( new Response<UserSpace>(null, "User not found!"), HttpStatus.NOT_FOUND); // Usuário não encontrado, erro 404
        }

        UserSpace permission = new UserSpace();

        permission.setPermissionLevel(2); //? USUÁRIO MEMBRO - NÍVEL DE PERMISSÃO (2) */
        permission.setSpace(found);
        permission.setUser(foundUser);

        permissionRepo.save(permission);

        return new ResponseEntity<>(new Response<>(permission, "User added to the space successfully!"), HttpStatus.OK); // Usuário adicionado ao Space com sucesso, status 200
    }


    @Override
    public ResponseEntity<Response<Space>> patchPermission(UserSpace userSpace) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'patchPermission'");
    }

    @Override
    public ResponseEntity<Response<Space>> deleteSpace(Space space) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteSpace'");
    }

    
}
