package com.duo.duo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.duo.duo.model.Space;

@Repository
public interface SpaceRepository extends JpaRepository<Space, Long>{
    public Space findByName(String name);
}
