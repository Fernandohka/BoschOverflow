package com.duo.duo.services.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

import com.duo.duo.dto.space.SpaceCreation;
import com.duo.duo.dto.space.SpaceResponse;
import com.duo.duo.model.Space;
import com.duo.duo.model.UserSpace;
import com.duo.duo.repositories.SpaceRepository;
import com.duo.duo.services.SpaceActionsService;


//* SpaceResponseDTO - record que retorna um objeto genérico T e uma mensagem */
 
public class SpaceActionImplementation implements SpaceActionsService<SpaceResponse<Space>>{

    @Autowired
    SpaceRepository repo;

    @Override
    //* recebe uma DTO SpaceCreation (String name) e retorna um SpaceResponse do tipo <Space> com o objeto criado e uma mensagem*/
    public ResponseEntity<SpaceResponse<Space>> postSpace(SpaceCreation space) {
        var found = repo.findByName(space.name());

        if (found != null) {
            /* caso já exista um space com esse nome, retorna um objeto vazio e uma mensagem de erro */
            return new ResponseEntity<>( new SpaceResponse<Space>(null, "There is already a space with that name. Choose another!"), HttpStatus.UNPROCESSABLE_ENTITY); // Não se pode criar um espaço com nome já existente, status 422
        }

        Space newSpace = new Space();
        newSpace.setName(space.name());
        repo.save(newSpace);

        return new ResponseEntity<>( new SpaceResponse<Space>(newSpace, "Space created successfully!"), HttpStatus.CREATED); // Space criado com sucesso, status 201

    }

    @Override
    public ResponseEntity<Space> addUser(UserSpace userSpace) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addUser'");
    }

    @Override
    public ResponseEntity<Space> patchPermission(UserSpace userSpace) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'patchPermission'");
    }

    @Override
    public ResponseEntity<Space> deleteSpace(Space space) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteSpace'");
    }

    
}
