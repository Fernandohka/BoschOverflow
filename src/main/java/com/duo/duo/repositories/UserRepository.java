package com.duo.duo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.duo.duo.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

    public List<User> findByName(String name);
    public List<User> findByMail(String mail);
    public List<User> findByEdv(String edv);

    @Query("SELECT u FROM User u WHERE u.name = :login OR u.mail = :login OR u.edv = :login")
    public List<User> loginMailOrNameOrEDV(@Param("login") String login);
}
