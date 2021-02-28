package com.javacruitment.rest.service.aop;

import com.javacruitment.common.exceptions.UserAlreadyExistsException;
import com.javacruitment.common.exceptions.UserNotFoundException;
import com.javacruitment.common.exceptions.UsernameIsOnBlacklistException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

@Slf4j
@ControllerAdvice
class RestExceptionHandler {

	@ExceptionHandler(UserNotFoundException.class)
	ResponseEntity<Problem> handleNotFound(UserNotFoundException ex) {
		return handleException(ex, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	ResponseEntity<Problem> handleNotValidArgument() {
		Exception ex = new RuntimeException("Username and email cannot be blank, email must have proper format");

		return handleException(ex, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(UserAlreadyExistsException.class)
	ResponseEntity<Problem> handleUserAlreadyExists(UserAlreadyExistsException ex) {
		return handleException(ex, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(UsernameIsOnBlacklistException.class)
	ResponseEntity<Problem> handleUserAlreadyExists(UsernameIsOnBlacklistException ex) {
		return handleException(ex, HttpStatus.BAD_REQUEST);
	}

	private ResponseEntity<Problem> handleException(Exception exception, HttpStatus httpStatus) {
		log.error(exception.getMessage(), exception);

		Problem problem = new Problem(httpStatus.value(), httpStatus.getReasonPhrase(), exception.getMessage());

		return ResponseEntity.status(httpStatus)
				.contentType(MediaType.APPLICATION_PROBLEM_JSON)
				.body(problem);
	}
}
