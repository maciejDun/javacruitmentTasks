package com.javacruitment.core.users;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doReturn;

import java.util.UUID;

import com.javacruitment.common.exceptions.UserNotFoundException;
import com.javacruitment.dao.entities.UserEntity;
import com.javacruitment.dao.users.UserDao;
import com.javacruitment.rest.model.User;
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
		UUID uuid = UUID.fromString("871f2ba4-a1e8-43a5-ada9-809e445aa41a");

		UserEntity userEntity = UserEntity.builder()
				.id(uuid)
				.username("John")
				.email( "john@doe.com")
				.build();
		doReturn(userEntity).when(userDao).findOrDie(uuid);

		User user = userService.getUser(uuid);
		assertEquals(userEntity.getId(), user.getId());
		assertEquals(userEntity.getUsername(), user.getUsername());
		assertEquals(userEntity.getEmail(), user.getEmail());
	}
}
