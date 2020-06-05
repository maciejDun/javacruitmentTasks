package com.interview.template.controller;

import java.util.List;

import com.interview.template.exceptions.UserNotFoundException;
import com.interview.template.model.UserEntity;
import com.interview.template.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(UserController.BASE_URL)
@AllArgsConstructor
class UserController {

	static final String BASE_URL = "/api/v1/users";

	private final UserService userService;

	@GetMapping
	public List<UserEntity> getAllUsers() {
		return userService.getAllUsers();
	}

	@GetMapping("/{userId}")
	UserEntity getUser(@PathVariable Long userId) throws UserNotFoundException {
		return userService.getUser(userId);
	}

	@RequestMapping(method = RequestMethod.HEAD, value = "/{userId}")
	void checkExists(@PathVariable Long userId) throws UserNotFoundException {
		userService.checkUserExists(userId);
	}
}
