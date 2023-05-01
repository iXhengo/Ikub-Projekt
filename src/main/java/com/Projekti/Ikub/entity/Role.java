package com.Projekti.Ikub.entity;


import com.Projekti.Ikub.exceptions.NotFoundException;
import lombok.AllArgsConstructor;

import java.util.Arrays;

@AllArgsConstructor

public enum Role {
    CUSTOMER("CUSTOMER"),
    ADMIN("ADMIN");

    private String value;

    public static Role fromValue(String value){
        return Arrays.asList(Role.values())
                .stream().filter(r -> r.value.equals(value))
                .findFirst()
                .orElseThrow(()-> new NotFoundException(String
                        .format("Role %s not found",value)));
    }

    public String getValue() {
        return value;
    }
}


