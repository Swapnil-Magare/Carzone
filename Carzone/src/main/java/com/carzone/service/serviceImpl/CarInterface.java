package com.carzone.service.serviceImpl;

import com.carzone.dto.CarDto;
import com.carzone.dto.ResponseStructure;
import com.carzone.model.Car;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface CarInterface {

    ResponseEntity<ResponseStructure<CarDto>> addCar(CarDto carDto);

    ResponseEntity<ResponseStructure<CarDto>> getBycarId(Long id);

    ResponseEntity<ResponseStructure<List<CarDto>>> getAllCar(int pageNumber, int pageSize);

    ResponseEntity<ResponseStructure<CarDto>> updateCar(long id, CarDto carDto);

    ResponseEntity<ResponseStructure<Optional<Car>>> deleteByCarId(Long id);

    ResponseEntity<ResponseStructure<CarDto>> getByCarModel(String model);
}
