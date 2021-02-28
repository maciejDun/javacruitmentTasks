package com.javacruitment.dao.users;

import com.javacruitment.common.exceptions.UserAlreadyExistsException;
import com.javacruitment.common.exceptions.UsernameIsOnBlacklistException;
import com.javacruitment.dao.entities.UserEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class UserDaoTest {

    @Mock
    private UserRepository userRepository;
    private final List<String> blackListForMockTests = List.of("admin", "administrator", "root");

    private UserDao userDao;

    @BeforeEach
    void beforeEach(){
        userDao = new UserDao(userRepository, blackListForMockTests);
    }


    @Test
    void shouldNotCreateUserWithNameAdmin() {
        //given
        UserEntity userEntity = UserEntity.builder()
                .username("admin")
                .email("admin@gmail.com")
                .build();

        //then
        assertThrows(UsernameIsOnBlacklistException.class, () -> userDao.create(userEntity));
    }

    @Test
    void shouldNotCreateUserWithNameAdministrator() {
        //given
        UserEntity userEntity = UserEntity.builder()
                .username("administrator")
                .email("administrator@gmail.com")
                .build();

        //then
        assertThrows(UsernameIsOnBlacklistException.class, () -> userDao.create(userEntity));
    }

    @Test
    void shouldNotCreateUserWithNameRoot() {
        //given
        UserEntity userEntity = UserEntity.builder()
                .username("root")
                .email("root@gmail.com")
                .build();

        //then
        assertThrows(UsernameIsOnBlacklistException.class, () -> userDao.create(userEntity));
    }

    @Test
    void shouldCreateUserWithCorrectUsernameAndEmail() throws UserAlreadyExistsException, UsernameIsOnBlacklistException {
        //given
        UserEntity userEntity = UserEntity.builder()
                .username("Bill")
                .email("bill@gmail.com")
                .build();

        //when
        when(userRepository.save(userEntity)).thenReturn(userEntity);

        //then
        assertEquals(userEntity, userDao.create(userEntity));
        assertDoesNotThrow(() -> userDao.create(userEntity));
    }

    @Test
    void shouldReturnTrueIfUsernameAlreadyExists() {
        //given
        UserEntity userEntity = UserEntity.builder()
                .username("Rob")
                .email("rob@gmail.com")
                .build();

        //when
        when(userRepository.existsByUsername(userEntity.getUsername())).thenReturn(true);

        //then
        assertThrows(UserAlreadyExistsException.class, () -> userDao.create(userEntity));
    }

    @Test
    void shouldReturnTrueIfEmailAlreadyExists() {
        // given
        UserEntity userEntity = UserEntity.builder()
                .username("Bill")
                .email("bill@gmail.com")
                .build();

        //when
        when(userRepository.existsByEmail(userEntity.getEmail())).thenReturn(true);

        //then
        assertThrows(UserAlreadyExistsException.class, () -> userDao.create(userEntity));
    }

}