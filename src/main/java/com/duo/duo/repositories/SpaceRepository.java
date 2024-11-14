package com.duo.duo.repositories;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.duo.duo.model.Space;

@Repository
public interface SpaceRepository extends JpaRepository<Space, Long>{
    public Space findByName(String name);

    public List<Space> findByNameContains(String name, PageRequest req);
}
