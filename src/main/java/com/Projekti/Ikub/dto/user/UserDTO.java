package com.Projekti.Ikub.dto.user;


import com.Projekti.Ikub.dto.bill.BillDTO;
import com.Projekti.Ikub.dto.car.CarDTO;
import com.Projekti.Ikub.entity.Bill;
import com.Projekti.Ikub.entity.Car;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDTO{

private Integer id;
@NotNull(message = "Name is requried")
private String name;
@NotNull(message = "Surname is required")
private String surname;
@NotNull(message = "Email is required")
private String email;
@NotNull(message = "Password is required")
private String password;
private List<CarDTO> carDTOS;

@JsonIgnore
private List<BillDTO> billDTOS;

}

