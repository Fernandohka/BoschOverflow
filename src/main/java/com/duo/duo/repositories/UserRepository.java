package com.duo.duo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.duo.duo.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
}
