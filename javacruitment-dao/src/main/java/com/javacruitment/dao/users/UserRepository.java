package com.javacruitment.dao.users;

import java.util.UUID;

import com.javacruitment.dao.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface UserRepository extends JpaRepository<UserEntity, UUID> {
}
