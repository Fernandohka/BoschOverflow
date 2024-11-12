package com.duo.duo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.duo.duo.model.User;
import com.duo.duo.model.UserSpace;

@Repository
public interface UserSpaceRepository extends JpaRepository<UserSpace, Long>{
    public User findOneByEmailOrEdv(String email, String edv);                  //Para utilizar no login independente do método escolhido pelo usuário
}
