package com.Projekti.Ikub.controller;

import com.Projekti.Ikub.dto.user.UserUpdateDTO;
import com.Projekti.Ikub.entity.User;
import com.Projekti.Ikub.repository.UserRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.Projekti.Ikub.BaseTest;
import com.Projekti.Ikub.dto.user.UserDTO;
import com.Projekti.Ikub.service.UserService;
import com.nimbusds.jose.proc.SecurityContext;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest extends BaseTest {


    @MockBean
    private UserService userService;

    @MockBean
    private UserRepository userRepository;

    @Test
    @Disabled
    public void test_registerUser_ok() throws Exception {
        SecurityContextHolder.getContext().setAuthentication(getAuthentication("ROLE_ADMIN"));
        Mockito.doReturn(new UserDTO()).when(userService).registerUser(Mockito.any(),Mockito.any());
        mvc.perform(MockMvcRequestBuilders.post("/users/admin/CUSTOMER")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(new UserDTO())))
                .andExpect(status().isOk());
    }

    @Test
    public void test_registerUser_ko() throws Exception{
        SecurityContextHolder.getContext().setAuthentication(getAuthentication("ROLE_CUSTOMER"));
        doReturn(new UserDTO()).when(userService).registerUser(any(),any());
        mvc.perform(MockMvcRequestBuilders.post("/users/admin/ADMIN")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(new UserDTO())))
                .andExpect(status().is4xxClientError());
    }
@Test
    public void test_updateUser_ok() throws Exception{
        SecurityContextHolder.getContext().setAuthentication(getAuthentication("ROLE_ADMIN"));
        doReturn(Optional.of(new User())).when(userRepository).findById(any());
        doReturn(new UserUpdateDTO()).when(userService).updateUser(any(),any());
        mvc.perform(MockMvcRequestBuilders.put("/users/admin/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(new UserUpdateDTO(1,"test","testSur","test@gmail.com"))))
                .andExpect(status().isOk());
}

    @Test
    public void test_findUser_ok() throws Exception{
        SecurityContextHolder.getContext().setAuthentication(getAuthentication("ROLE_ADMIN"));
        doReturn(new User()).when(userService).findById(any());
        mvc.perform(MockMvcRequestBuilders.get("/users/admin/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
