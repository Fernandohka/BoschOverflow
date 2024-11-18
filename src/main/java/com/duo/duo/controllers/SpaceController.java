package com.duo.duo.controllers;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.duo.duo.dto.Token;
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
    public ResponseEntity<Space> postSpace(@RequestBody SpaceCreation space, @RequestAttribute("token") Token token) {

        if (spaceService.checkSpaceNameForPost(space.name()))
            return new ResponseEntity<>(null, HttpStatus.CONFLICT); // se tiver um com o memso nome não cria

        if (!spaceService.checkForUser(token.getId()))
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);

        var created = spaceService.postSpace(space, token.getId());
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    
    @PostMapping("/permission/post") //  o correto era criar outro controlador com os serviços somente de Permissão/ UserService 
                                     //  mas estamos atrasados e não tem interface de UserService pronta e estamos com pouco tempo beijinhos de luz da kau e amilton

                                     //  GERENCIADOR DE PERMISSÕES : pode adicionar novos usuários ao epsaço ou modificar permissões dos membros dentro desse espaço
                                     
    public ResponseEntity<UserSpace> manageSpaceUsers(@RequestBody AddUserToSpace permission, @RequestAttribute("token") Token token) {

        if (!spaceService.checkForUser(permission.userId()) || !spaceService.checkForSpace(permission.spaceId()))   //  se o token não conter um usuário válido estoura um erro 401 - NÃO AUTORIZADO 
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);                                     //  se tiver um com o meso nome não cria

        
        if (spaceService.checkUserPermission(token.getId(), permission.spaceId()) != null) {

            if (spaceService.checkUserPermission(token.getId(), permission.spaceId()) != 3) {
                System.out.println("caiu aqui hahaha 403 no ce");
                return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);                                    //  se o id do token não for adm do espaço ele não pode realizar a operação

            }
        }


        var created = spaceService.manageSpaceUsers(permission);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @GetMapping("/get")
    public ResponseEntity<ArrayList<Space>> getSpaces(@RequestParam String query,
                                                      @RequestParam int page,
                                                      @RequestParam int limit) {

        return null;
    }
    
}
