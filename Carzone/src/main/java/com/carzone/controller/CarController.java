package com.carzone.controller;

import com.carzone.dto.CarDto;
import com.carzone.dto.ResponseStructure;
import com.carzone.model.Car;
import com.carzone.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/car")
public class CarController {

    @Autowired
    private CarService carService;

    @PostMapping("/add")
    public ResponseEntity<ResponseStructure<CarDto>> addCar(@RequestBody CarDto carDto) {
        return carService.addCar(carDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseStructure<CarDto>> getByCarId(@PathVariable Long id) {
        return carService.getBycarId(id);
    }

    @GetMapping
    public ResponseEntity<ResponseStructure<List<CarDto>>> getAllCar() {
        return carService.getAllCar();
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ResponseStructure<CarDto>> updateCar(@PathVariable long id, @RequestBody CarDto carDto) {
        return carService.updateCar(id, carDto);
    }

    @DeleteMapping("/deleteById/{id}")
    public ResponseEntity<ResponseStructure<Optional<Car>>> deleteCar(@PathVariable Long id) {
      return carService.deleteByCarId(id);
    }
}
