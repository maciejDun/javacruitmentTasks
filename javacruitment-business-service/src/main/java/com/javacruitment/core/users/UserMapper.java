package com.javacruitment.core.users;

import java.time.LocalDateTime;

import com.javacruitment.dao.entities.UserEntity;
import com.javacruitment.rest.model.User;
import com.javacruitment.rest.model.UserUpsert;

class UserMapper {
	public User map(UserEntity user) {
		return User.builder()
				.id(user.getId())
				.username(user.getUsername())
				.email(user.getEmail())
				.creationDate(user.getCreationDate())
				.build();
	}

	public UserEntity map(UserUpsert user) {
		return UserEntity.builder()
				.username(user.getUsername())
				.email(user.getEmail())
				.creationDate(LocalDateTime.now())
				.build();
	}
}
