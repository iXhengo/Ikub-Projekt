package com.Projekti.Ikub.controller;


import com.Projekti.Ikub.BaseTest;
import com.Projekti.Ikub.dto.car.CarDTO;
import com.Projekti.Ikub.dto.user.UserDTO;
import com.Projekti.Ikub.entity.Car;
import com.Projekti.Ikub.entity.User;
import com.Projekti.Ikub.repository.CarRepository;
import com.Projekti.Ikub.repository.UserRepository;
import com.Projekti.Ikub.service.CarService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;



import java.util.ArrayList;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class CarControllerTest extends BaseTest {

    @MockBean
    private CarService carService;
    @MockBean
    private CarRepository carRepository;
    @MockBean
    private UserRepository userRepository;


    @Test
    public void test_addCar_ok() throws Exception {
        SecurityContextHolder.getContext().setAuthentication(getAuthentication("ROLE_CUSTOMER"));
        doReturn(new CarDTO()).when(carService).addCar(Mockito.any());
        mvc.perform(MockMvcRequestBuilders.post("/cars/user/addCar")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(new CarDTO())))
                .andExpect(status().isOk());
    }

    @Test
    public void test_findCarById_ok() throws Exception{

        SecurityContextHolder.getContext().setAuthentication(getAuthentication("ROLE_ADMIN"));
        doReturn(new CarDTO()).when(carService).findCarById(any());
        mvc.perform(MockMvcRequestBuilders.get("/cars/admin/car/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
    @Test
    public void test_findCarByUserId_ko() throws Exception{

        SecurityContextHolder.getContext().setAuthentication(getAuthentication("ROLE_ADMIN"));
        User fakeUser = new User();
        doReturn(Optional.of(fakeUser)).when(userRepository).findById(any());
        doReturn(new ArrayList<CarDTO>()).when(carService).findCarByUserId(fakeUser.getId());
        mvc.perform(MockMvcRequestBuilders.get("/cars/admin/userCar/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }


    @Test
    public void test_addCarToUser_ko() throws Exception{

        SecurityContextHolder.getContext().setAuthentication(getAuthentication("ROLE_ADMIN"));
        User fakeUser = new User();
        doReturn(Optional.of(fakeUser)).when(userRepository).findById(any());
        CarDTO fakeCar = new CarDTO();
        doNothing().when(carService).addCarToUser(fakeCar,fakeUser.getId());
        mvc.perform(MockMvcRequestBuilders.post("/cars/user/car/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError());

    }
    }

