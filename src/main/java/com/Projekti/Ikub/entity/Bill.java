package com.Projekti.Ikub.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "bill")
public class Bill {

    @Id
    @GeneratedValue
    private Integer id;
    private Double price;
    @CreatedDate
    private LocalDateTime createdAt;
    @Column(name = "paid")
    private Boolean status= false;

   @ManyToOne
   @JoinColumn(name = "user_id",referencedColumnName = "id")
    private User user;

    @PrePersist
    public void prePersist(){
        createdAt = LocalDateTime.now();
    }

}
