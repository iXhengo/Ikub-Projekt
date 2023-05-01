package com.Projekti.Ikub.mapper;

import com.Projekti.Ikub.dto.car.CarDTO;
import com.Projekti.Ikub.entity.Car;
import com.Projekti.Ikub.entity.User;

import java.util.List;

public class CarMapper {

    public static CarDTO toDto(Car car) {

        return CarDTO.builder()
                .id(car.getId())
                .chassis(car.getChassis())
                .make(car.getMake())
                .enginePower(car.getEnginePower())
                .producedAt(car.getProducedAt())
                .createdAt(car.getCreatedAt())
                .build();
    }


    public static Car toEntity(CarDTO carDTO) {
        return Car.builder()
                .id(carDTO.getId())
                .make(carDTO.getMake())
                .chassis(carDTO.getChassis())
                .enginePower(carDTO.getEnginePower())
                .producedAt(carDTO.getProducedAt())
                .createdAt(carDTO.getCreatedAt())
                .build();
    }

    public static Car toAddforEntity(CarDTO carDTO, Integer u) {
        return Car.builder()
                .id(carDTO.getId())
                .make(carDTO.getMake())
                .chassis(carDTO.getChassis())
                .enginePower(carDTO.getEnginePower())
                .producedAt(carDTO.getProducedAt())
                .createdAt(carDTO.getCreatedAt())
                .user(User.builder()
                        .id(u).build())
                .build();

    }
}
