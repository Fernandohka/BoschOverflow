package com.duo.duo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.duo.duo.dto.space.SpaceCreation;
import com.duo.duo.model.Space;
import com.duo.duo.services.implementations.SpaceActionImplementation;

@RestController
@RequestMapping("/space")
public class SpaceController {

    @Autowired
    SpaceActionImplementation spaceService;


    @PostMapping("/post")
    public ResponseEntity<Space> postSpace(@RequestBody SpaceCreation space) {

        if (!spaceService.checkSpaceNameForPost(space.name()))
            return new ResponseEntity<>(null, HttpStatus.CONFLICT); // se tiver um com o memso nome não cria

        var created = spaceService.postSpace(space);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }
    
}
