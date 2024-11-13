package com.duo.duo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.duo.duo.model.User;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

    List<User> findByName(String name);
    List<User> findByMail(String mail);
    List<User> findByEdv(String edv);
}
