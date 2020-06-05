package com.interview.template.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doReturn;

import com.interview.template.dao.UserDao;
import com.interview.template.exceptions.UserNotFoundException;
import com.interview.template.model.UserEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
class UserServiceTest {

	@Mock
	private UserDao userDao;

	private UserService userService;

	@BeforeEach
	void beforeEach() {
		userService = new UserService(userDao);
	}

	@Test
	void shouldFindUser() throws UserNotFoundException {
		UserEntity user = UserEntity.builder()
				.id(1L)
				.username("john")
				.password("pass")
				.build();
		doReturn(user).when(userDao).findOrDie(1L);

		assertEquals(user, userService.getUser(1L));
	}
}
