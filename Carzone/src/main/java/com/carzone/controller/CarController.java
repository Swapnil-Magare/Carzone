package com.carzone.controller;

import com.carzone.dto.CarDto;
import com.carzone.model.Car;
import com.carzone.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/car")
public class CarController {

    @Autowired
    private CarService carService;

    @PostMapping("/add")
    public String addCar(@RequestBody CarDto carDto) {
        carService.addCar(carDto);
        return "Car added successfully with associated company!";
    }

    @GetMapping("/{id}")
    public Optional<Car> getByCarId(@PathVariable Long id) {
        return carService.getBycarId(id);
    }

    @GetMapping
    public List<Car> getAllCar() {
        return carService.getAllCar();
    }

    @PutMapping("/update/{id}")
    public CarDto updateCar(@PathVariable long id, @RequestBody CarDto carDto) {
        return carService.updateCar(id, carDto);
    }

    @DeleteMapping("/deleteById/{id}")
    public void deleteCar(@PathVariable Long id) {
        carService.deleteByCarId(id);
    }
}
