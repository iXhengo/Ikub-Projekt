package com.Projekti.Ikub.mapper.user;

import com.Projekti.Ikub.dto.user.UserDTO;
import com.Projekti.Ikub.dto.user.UserUpdateDTO;
import com.Projekti.Ikub.entity.Car;
import com.Projekti.Ikub.entity.User;
import com.Projekti.Ikub.mapper.BillMapper;
import com.Projekti.Ikub.mapper.CarMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class UserMapper {

    public static UserDTO toDto(User u){
        return UserDTO.builder()
                .id(u.getId())
                .name(u.getName())
                .email(u.getEmail())
                .surname(u.getSurname())
                .carDTOS(u.getCarList()!=null?u.getCarList().stream().map(CarMapper::toDto).collect(Collectors.toList()):null)
                .build();
    }

    public static User toEntity(UserDTO userDTO) {
        List<Car> cars = new ArrayList<>();
        User u =  User.builder()
                .id(userDTO.getId())
                .name(userDTO.getName())
                .surname(userDTO.getSurname())
                .email(userDTO.getEmail())
                .password(userDTO.getPassword())
                .carList(userDTO.getCarDTOS()!=null?userDTO.getCarDTOS().stream().map(CarMapper::toEntity).collect(Collectors.toList()) : null)
                .billList(userDTO.getBillDTOS()!=null?userDTO.getBillDTOS().stream().map(BillMapper::toEntity).collect(Collectors.toList()) : null)
                .build();
        return u;
    }
   public static UserUpdateDTO toUpdateDto(User u){
        return UserUpdateDTO.builder()
                .id(u.getId())
                .name(u.getName())
                .surname(u.getSurname())
                .email(u.getEmail())
                .build();
   }

   public static User buildUpdateUser(UserUpdateDTO userUpdateDTO,User u){
        u.setName(userUpdateDTO.getName());
        u.setSurname(userUpdateDTO.getSurname());
        u.setEmail(userUpdateDTO.getEmail());
            return u;

   }
}
