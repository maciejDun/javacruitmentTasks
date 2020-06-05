package com.interview.template.dao;

import java.util.List;
import java.util.Optional;

import com.interview.template.exceptions.UserNotFoundException;
import com.interview.template.model.UserEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@AllArgsConstructor
public class UserDao {
	private final UserRepository userRepository;

	public List<UserEntity> findAll() {
		return userRepository.findAll();
	}

	public Optional<UserEntity> find(long id) {
		return userRepository.findById(id);
	}

	public UserEntity findOrDie(long id) throws UserNotFoundException {
		return find(id).orElseThrow(() -> new UserNotFoundException("User with id " + id + " does not exist."));
	}

	public void checkExists(long id) throws UserNotFoundException {
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
}
