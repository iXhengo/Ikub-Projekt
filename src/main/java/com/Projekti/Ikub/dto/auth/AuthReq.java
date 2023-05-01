package com.Projekti.Ikub.dto.auth;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthReq {

    @NotNull @Email
    private String email;
    @NotNull
    private String password;
}
