package com.Projekti.Ikub.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "car")
public class Car {

    @Id
    @GeneratedValue
    private Integer id;
    @Column(name = "numer_shasie")
    private String chassis;
    @Column(name = "viti_prodhimit")
    private LocalDate producedAt;
    @Column(name = "fuqi_motorrike")
    private Integer enginePower;
    @Column(name = "make")
    private String make;
    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "user_id",referencedColumnName = "id")
    private User user;

    @PrePersist
    public void prePersist(){
        createdAt = LocalDateTime.now();
    }


}
