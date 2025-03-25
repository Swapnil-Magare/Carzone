package com.carzone.controller;

import com.carzone.dto.CarDto;
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

    @PostMapping("/{id}/add")
    public Car addCar(@PathVariable long id, @RequestBody Car car){
        return carService.addCar(id,car);
    }

    @GetMapping
    public List<Car> getAllCar(){
        return carService.getAllCar();
    }

    @GetMapping("/{id}")
    public Optional<Car> getCarById(@PathVariable long id){
        return carService.getCarById(id);
    }

    @PutMapping("/update/{id}")
    public CarDto updateCar(@PathVariable long id, @RequestBody CarDto carDto){
        return carService.updateCar(id, carDto);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteCar(@PathVariable long id){
        carService.deleteCar(id);
    }
}
