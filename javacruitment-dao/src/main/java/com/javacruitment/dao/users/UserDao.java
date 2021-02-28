package com.javacruitment.dao.users;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.javacruitment.common.exceptions.UserAlreadyExistsException;
import com.javacruitment.common.exceptions.UserNotFoundException;
import com.javacruitment.common.exceptions.UsernameIsOnBlacklistException;
import com.javacruitment.dao.entities.UserEntity;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

@Repository
@AllArgsConstructor
public class UserDao {
	private final UserRepository userRepository;

	@Value("${blacklist.list}")
	private final List<String> blacklist;

	public List<UserEntity> findAll() {
		return userRepository.findAll();
	}

	public UserEntity findOrDie(UUID id) throws UserNotFoundException {
		return find(id).orElseThrow(() -> new UserNotFoundException("User with id " + id + " does not exist."));
	}

	private Optional<UserEntity> find(UUID id) {
		return userRepository.findById(id);
	}

	public void checkExists(UUID id) throws UserNotFoundException {
		if (!userRepository.existsById(id)) {
			throw new UserNotFoundException("User with id " + id + " does not exist.");
		}
	}

	public List<UserEntity> findUserByGivenText(String text) throws UserNotFoundException{
		if (userRepository.findUserByGivenText(text).isEmpty()){
			throw new UserNotFoundException("User with given text: " + text + ", does not exist");
		}
		return userRepository.findUserByGivenText(text);
	}

	public UserEntity create(UserEntity user) throws UserAlreadyExistsException, UsernameIsOnBlacklistException {
		if (user.getId() != null) {
			throw new IllegalArgumentException("User already exists.");
		}
		if (checkIfUsernameNameIsOnBlacklist(user)){
			throw new UsernameIsOnBlacklistException("This username is restricted");
		}
		if (userRepository.existsByUsername(user.getUsername())) {
			throw new UserAlreadyExistsException("Username already exists");
		}
		if (userRepository.existsByEmail(user.getEmail())) {
			throw new UserAlreadyExistsException("Email already exists");
		}
		return userRepository.save(user);
	}

	private boolean checkIfUsernameNameIsOnBlacklist(UserEntity user){
		return blacklist.contains(user.getUsername().toLowerCase());
	}

	public void delete(UserEntity user) {
		userRepository.delete(user);
	}

}
