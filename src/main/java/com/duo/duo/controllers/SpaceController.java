package com.duo.duo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.duo.duo.dto.space.AddUserToSpace;
import com.duo.duo.dto.space.SpaceCreation;
import com.duo.duo.model.Space;
import com.duo.duo.model.UserSpace;
import com.duo.duo.services.SpaceActionsService;

@RestController
@RequestMapping("/space")
public class SpaceController {

    @Autowired
    SpaceActionsService spaceService;


    @PostMapping("/post")
    public ResponseEntity<Space> postSpace(@RequestBody SpaceCreation space) {

        if (!spaceService.checkSpaceNameForPost(space.name()))
            return new ResponseEntity<>(null, HttpStatus.CONFLICT); // se tiver um com o memso nome não cria

        var created = spaceService.postSpace(space);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    
    @PostMapping("/permission/post") //? o correto era criar outro controlador com os serviços somente de Permissão/ UserService 
                                     //? mas estamos atrasados e não tem interface de UserService pronta e estamos com pouco tempo beijinhos de luz da kau e amilton
                                     
    public ResponseEntity<UserSpace> addUser(@RequestBody AddUserToSpace permission) {

        if (!spaceService.checkForUser(permission.userId()) || !spaceService.checkForUser(permission.userId()))
            return new ResponseEntity<>(null, HttpStatus.CONFLICT); // se tiver um com o memso nome não cria

        var created = spaceService.addUser(permission);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }
    
}
