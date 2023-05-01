package com.Projekti.Ikub.repository;

import com.Projekti.Ikub.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CarRepository extends JpaRepository<Car,Integer> {

    @Modifying
    @Query("delete from Car c where c.id = :id")
    void deleteById(@Param("id") Integer id);

}
