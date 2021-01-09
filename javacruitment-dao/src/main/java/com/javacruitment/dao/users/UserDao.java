package com.javacruitment.dao.users;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.javacruitment.common.exceptions.UserNotFoundException;
import com.javacruitment.dao.entities.UserEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@AllArgsConstructor
public class UserDao {
	private final UserRepository userRepository;

	public List<UserEntity> findAll() {
		return userRepository.findAll();
	}

	public Optional<UserEntity> find(UUID id) {
		return userRepository.findById(id);
	}

	public UserEntity findOrDie(UUID id) throws UserNotFoundException {
		return find(id).orElseThrow(() -> new UserNotFoundException("User with id " + id + " does not exist."));
	}

	public void checkExists(UUID id) throws UserNotFoundException {
		if (!userRepository.existsById(id)) {
			throw new UserNotFoundException("User with id " + id + " does not exist.");
		}
	}

	public UserEntity create(UserEntity user) {
		if (user.getId() != null) {
			throw new IllegalArgumentException("User already exists.");
		}
		return userRepository.save(user);
	}

	public void delete(UserEntity user) {
		userRepository.delete(user);
	}
}
