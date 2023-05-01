package com.Projekti.Ikub.dto.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserUpdateDTO {
    private Integer id;
    @NotNull(message = "Name is required")
    private String name;
    @NotNull(message = "Surname is required")
    @NotEmpty(message ="username cannot be empty")
    private String surname;
    @NotNull(message = "Email is required")
    @Email(message = "Email is not valid format")
    private String email;
}
