package com.duo.duo.services.implementations;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.duo.duo.dto.UserDto.NewUserDto;
import com.duo.duo.model.User;
import com.duo.duo.repositories.UserRepository;
import com.duo.duo.services.UserService;

public class UserImplementation implements UserService {

    @Autowired
    UserService service;

    @Autowired
    UserRepository userRepo;

    @Override
    public ResponseEntity<User> CreateUser(NewUserDto newUserData) {

        List<User> users = userRepo.findByName(newUserData.name());

        if (users.isEmpty()) {
            return new 
        }

        User newUser = new User();

        return new ResponseEntity<>(newUser, HttpStatus.OK);
    }

    @Override
    public void DeleteUser(User user) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'DeleteUser'");
    }
    
}
