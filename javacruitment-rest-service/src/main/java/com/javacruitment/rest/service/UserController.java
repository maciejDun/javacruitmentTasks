package com.javacruitment.rest.service;

import static org.springframework.http.MediaType.APPLICATION_PROBLEM_JSON_VALUE;

import java.util.List;
import java.util.UUID;

import com.javacruitment.common.exceptions.UserAlreadyExists;
import com.javacruitment.common.exceptions.UserNotFoundException;
import com.javacruitment.core.users.UserService;
import com.javacruitment.rest.model.User;
import com.javacruitment.rest.model.UserUpsert;
import com.javacruitment.rest.service.aop.Problem;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(UserController.BASE_URL)
@AllArgsConstructor
class UserController {

	static final String BASE_URL = "/users";

	private final UserService userService;

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	@Operation(description = "Returns a list of users")
	@ApiResponses({
			@ApiResponse(responseCode = "200", description = "OK"),
	})
	public List<User> getAllUsers() {
		return userService.getAllUsers();
	}

	@GetMapping(path = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@Operation(description = "Returns user by id.")
	@ApiResponses({
			@ApiResponse(responseCode = "200", description = "OK"),
			@ApiResponse(responseCode = "404", description = "Not found", content = @Content(mediaType = APPLICATION_PROBLEM_JSON_VALUE, schema = @Schema(implementation = Problem.class)))
	})
	public User getUser(@PathVariable UUID id) throws UserNotFoundException {
		return userService.getUser(id);
	}

	@DeleteMapping("{id}")
	@Operation(description = "Deletes user.")
	@ApiResponses({
			@ApiResponse(responseCode = "204", description = "Deleted."),
			@ApiResponse(responseCode = "404", description = "Not found.",
					content = @Content(mediaType = APPLICATION_PROBLEM_JSON_VALUE, schema = @Schema(implementation = Problem.class)))
	})
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteUser(@PathVariable UUID id) throws UserNotFoundException {
		userService.deleteUser(id);
	}

	@RequestMapping(method = RequestMethod.HEAD, value = "{id}")
	@Operation(description = "Checks if user exists.")
	@ApiResponses({
			@ApiResponse(responseCode = "200", description = "OK"),
			@ApiResponse(responseCode = "404", description = "Not found", content = @Content(mediaType = APPLICATION_PROBLEM_JSON_VALUE, schema = @Schema(implementation = Problem.class)))
	})
	public void checkExists(@PathVariable UUID id) throws UserNotFoundException {
		userService.checkUserExists(id);
	}

	@PostMapping("/")
	@Operation(description = "Creates new user")
	@ApiResponses({
			@ApiResponse(responseCode = "200", description = "OK"),
			@ApiResponse(responseCode = "400", description = "Blank field", content = @Content(mediaType = APPLICATION_PROBLEM_JSON_VALUE, schema = @Schema(implementation = Problem.class)))
	})
	public UUID addUser(@RequestBody @Valid UserUpsert userUpsert) throws UserAlreadyExists {
		return userService.createUser(userUpsert);
	}
}
