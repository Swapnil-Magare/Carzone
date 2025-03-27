package com.carzone.repositoy;

import com.carzone.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {

    Optional<Car> findByModelAndCompanyId(String model, Long id);

    Optional<Car> findByModel(String model);
}
