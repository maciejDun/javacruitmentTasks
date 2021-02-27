package com.javacruitment.dao.users;

import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.UUID;

import com.javacruitment.common.exceptions.UserAlreadyExistsExists;
import com.javacruitment.common.exceptions.UserNotFoundException;
import com.javacruitment.common.exceptions.UsernameIsOnBlacklistException;
import com.javacruitment.dao.entities.UserEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@AllArgsConstructor
public class UserDao {
	private final UserRepository userRepository;

	private final List<String> blacklist = List.of("admin", "administrator", "root");

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

	public List<UserEntity> findUserByGivenText(String text) throws UserNotFoundException{
		if (userRepository.findUserByGivenText(text).isEmpty()){
			throw new UserNotFoundException("User with given text: " + text + ", does not exist");
		}
		return userRepository.findUserByGivenText(text);
	}

	public UserEntity create(UserEntity user) throws UserAlreadyExistsExists, UsernameIsOnBlacklistException {
		if (user.getId() != null) {
			throw new IllegalArgumentException("User already exists.");
		}
		if (checkIfUsernameNameIsOnBlacklist(user)){
			throw new UsernameIsOnBlacklistException("This username is restricted");
		}
		if (!userRepository.findByUsernameAndEmail(user.getUsername(), user.getEmail()).isEmpty()) {
			throw new UserAlreadyExistsExists("User already exists");
		}
		return userRepository.save(user);
	}

	public boolean checkIfUsernameNameIsOnBlacklist(UserEntity user){
		return blacklist.contains(user.getUsername().toLowerCase());
	}

	public void delete(UserEntity user) {
		userRepository.delete(user);
	}

}
