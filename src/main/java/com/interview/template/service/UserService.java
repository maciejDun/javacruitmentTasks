package com.interview.template.service;

import com.interview.template.dao.UserDao;
import com.interview.template.exceptions.UserNotFoundException;
import com.interview.template.model.UserEntity;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor(access = AccessLevel.PACKAGE)
public class UserService {

	private final UserDao userDao;

	public List<UserEntity> getAllUsers() {
		return userDao.findAll();
	}

	public UserEntity getUser(long id) throws UserNotFoundException {
		return userDao.findOrDie(id);
	}

	public void checkUserExists(long id) throws UserNotFoundException {
		userDao.checkExists(id);
	}
}
