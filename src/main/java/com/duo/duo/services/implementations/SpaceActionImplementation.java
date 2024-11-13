package com.duo.duo.services.implementations;

import org.springframework.http.ResponseEntity;

import com.duo.duo.model.Space;
import com.duo.duo.model.UserSpace;
import com.duo.duo.services.SpaceActionsService;

public class SpaceActionImplementation implements SpaceActionsService<Space>{

    @Override
    public ResponseEntity<Space> postSpace(Space space) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'postSpace'");
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
