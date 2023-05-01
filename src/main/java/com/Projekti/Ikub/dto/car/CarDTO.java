package com.Projekti.Ikub.dto.car;

import com.Projekti.Ikub.entity.Bill;
import com.Projekti.Ikub.entity.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.PrePersist;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CarDTO {

    private Integer id;
    @NotNull(message = "Field is required")
    private String chassis;
    private LocalDate producedAt;
    @NotNull(message = "Field is required")
    private Integer enginePower;
    @NotNull(message = "Field is required")
    private String make;
    @CreatedDate
    private LocalDateTime createdAt;
    @PrePersist
    public void prePersist(){
        createdAt = LocalDateTime.now();
    }



}
