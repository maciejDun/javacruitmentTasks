package com.javacruitment.dao.users;

import java.util.List;
import java.util.UUID;

import com.javacruitment.dao.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
interface UserRepository extends JpaRepository<UserEntity, UUID> {

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);

    @Query("SELECT e FROM UserEntity e WHERE e.username LIKE CONCAT('%',:text,'%')")
    List<UserEntity> findUserByGivenText(@Param("text")String text);

}
