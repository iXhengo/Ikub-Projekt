package com.Projekti.Ikub.service;


import com.Projekti.Ikub.BaseTest;
import com.Projekti.Ikub.dto.user.UserDTO;
import com.Projekti.Ikub.dto.user.UserUpdateDTO;
import com.Projekti.Ikub.entity.User;
import com.Projekti.Ikub.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.Assert;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class UserServiceTest extends BaseTest {

    @Autowired
    private UserService toTest;

    @MockBean
    private UserRepository userRepository;
    @MockBean
    private PasswordEncoder passwordEncoder;


    @Test
    public void test_findById_ok() {
        Mockito.doReturn(Optional.of(new User())).when(userRepository).findById(Mockito.anyInt());
        User out = toTest.findById(1);
        Assertions.assertNotNull(out);
    }

    @Test
    public void test_registerUser_ok() {
        Mockito.doReturn("anyPass").when(passwordEncoder).encode(Mockito.anyString());
        Mockito.doReturn(new User()).when(userRepository).save(Mockito.any());
        UserDTO out = toTest.registerUser(new UserDTO(), "ADMIN");
        Assertions.assertNotNull(out);
    }

    @Test
    public void test_updateUser_ok() {
        Mockito.doReturn(Optional.of(new User())).when(userRepository).findById(Mockito.any());
        Mockito.doReturn(new User()).when(userRepository).save(Mockito.any());
        UserUpdateDTO out = toTest.updateUser(Mockito.anyInt(), new UserUpdateDTO());
        Assertions.assertNotNull(out);
    }

}
