package com.Projekti.Ikub.dto.bill;


import com.Projekti.Ikub.dto.car.CarDTO;
import com.Projekti.Ikub.dto.user.UserDTO;
import com.Projekti.Ikub.entity.Car;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.PrePersist;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BillDTO {

    private Integer id;
    private Double price;
    private UserDTO userDTO;
    private Boolean status;
    @CreatedDate
    private LocalDateTime createdAt;
    @PrePersist
    public void prePersist(){
        createdAt = LocalDateTime.now();
    }


}
