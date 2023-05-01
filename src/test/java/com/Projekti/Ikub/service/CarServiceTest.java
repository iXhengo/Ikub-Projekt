package com.Projekti.Ikub.service;

import com.Projekti.Ikub.BaseTest;
import com.Projekti.Ikub.dto.car.CarDTO;
import com.Projekti.Ikub.entity.Car;
import com.Projekti.Ikub.entity.User;
import com.Projekti.Ikub.mapper.CarMapper;
import com.Projekti.Ikub.repository.CarRepository;
import com.Projekti.Ikub.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootTest
public class CarServiceTest extends BaseTest {

    @Autowired
    private CarService toTest;
    @MockBean
    private CarRepository carRepository;
    @MockBean
    private UserRepository userRepository;

    @Test
    public void test_addCar_ok() {
        Mockito.doReturn(new Car()).when(carRepository).save(Mockito.any());
        CarDTO c = toTest.addCar(new CarDTO());
        Assertions.assertNotNull(c);
    }

    @Test
    public void test_findCarById() {
        Mockito.doReturn(Optional.of(new Car())).when(carRepository).findById(Mockito.anyInt());
        CarDTO c = toTest.findCarById(1);
        Assertions.assertNotNull(c);
    }

    @Test
    public void test_findCarByUserId_ok() {
        User fakeUser = new User();
        Mockito.doReturn(Optional.of(fakeUser)).when(userRepository).findById(Mockito.any());
        Car fakeCar = new Car();
        Car fakeCar2 = new Car();
        List<Car> c = new ArrayList<>();
        c.add(fakeCar);
        c.add(fakeCar2);
        fakeUser.setCarList(c);
        toTest.findCarByUserId(fakeUser.getId());
        Assertions.assertEquals(2,c.size());
    }

    @Test
    public void test_addCarToUser_ok() {
        CarDTO fakeCar = new CarDTO();
        User fakeUser = new User();
        Mockito.doReturn(Optional.of(fakeUser)).when(userRepository).findById(Mockito.any());
        Car fakeC= new Car();
        Car fakeC2 = new Car();
        List<Car> c = new ArrayList<>();
        c.add(fakeC);
        c.add(fakeC2);
        fakeUser.setCarList(c);
        Mockito.doReturn(fakeCar).when(carRepository).save(CarMapper.toEntity(fakeCar));
        toTest.addCarToUser(fakeCar, fakeUser.getId());
        Assertions.assertEquals(3, fakeUser.getCarList().size());

    }

    @Test
    public void test_deleteCar_ok() {

        List<Car> fakeList = new ArrayList<Car>();
        Car fakeCar = new Car();
        Car fakeC= new Car();
        Car fakeC2 = new Car();
        fakeList.add(fakeCar);
        fakeList.add(fakeC);
        fakeList.add(fakeC2);
        Mockito.doReturn(Optional.of(fakeCar)).when(carRepository).findById(Mockito.any());
        Mockito.doNothing().when(carRepository).deleteById(Mockito.any());
        toTest.deleteCar(fakeCar.getId());
        Assertions.assertEquals(2, fakeList.size());
    }

}
