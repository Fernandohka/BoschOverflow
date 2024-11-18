package com.duo.duo.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.duo.duo.model.UserSpace;

@Repository
public interface UserSpaceRepository extends JpaRepository<UserSpace, Long>{
    public UserSpace findByUserId(Long userId);         //Para utilizar no login independente do método escolhido pelo usuário

    @Query(value = "SELECT * FROM user_space  WHERE user_id = :user_id and space_id = :space_id", nativeQuery = true)
    public Optional<UserSpace> findPermission(@Param("user_id") Long userId, @Param("space_id") Long spaceId);

}
