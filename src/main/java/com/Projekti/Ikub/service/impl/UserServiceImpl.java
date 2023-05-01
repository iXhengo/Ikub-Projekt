package com.Projekti.Ikub.service.impl;

import com.Projekti.Ikub.dto.car.CarDTO;
import com.Projekti.Ikub.dto.user.UserDTO;
import com.Projekti.Ikub.dto.user.UserUpdateDTO;
import com.Projekti.Ikub.entity.Car;
import com.Projekti.Ikub.entity.Role;
import com.Projekti.Ikub.entity.User;
import com.Projekti.Ikub.exceptions.NotFoundException;
import com.Projekti.Ikub.mapper.CarMapper;
import com.Projekti.Ikub.mapper.user.UserMapper;
import com.Projekti.Ikub.repository.CarRepository;
import com.Projekti.Ikub.repository.UserRepository;
import com.Projekti.Ikub.service.CarService;
import com.Projekti.Ikub.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static java.lang.String.format;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CarRepository carRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;



    @Override
    public User findById(Integer id) {
    return userRepository
            .findById(id)
            .orElseThrow(
                    () -> new NotFoundException(format("User not found")));
    }

    @Override
    public UserDTO registerUser(UserDTO userDTO, String userRole) {
        User u = UserMapper.toEntity(userDTO);
        u.setUserRole(userRole != null ? Role.fromValue(userRole) : Role.CUSTOMER);
        u.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        u = userRepository.save(u);
        List<CarDTO> cars = userDTO.getCarDTOS().stream().collect(Collectors.toList());

        for (CarDTO c : cars) {
            carRepository.save(CarMapper.toAddforEntity(c, u.getId()));
        }
        return UserMapper.toDto(u);
        }


    @Override
    public UserUpdateDTO updateUser(Integer id, UserUpdateDTO upd) {
        User u = userRepository
                .findById(id).orElseThrow(
                        () -> new NotFoundException(
                                format("User with id %s not found",id)));
        u = userRepository.save(UserMapper.buildUpdateUser(upd,u));
        return UserMapper.toUpdateDto(u);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email)
                .orElseThrow(
                        () -> new NotFoundException(
                                String.format("User with email %s not found",email)));
    }
}
