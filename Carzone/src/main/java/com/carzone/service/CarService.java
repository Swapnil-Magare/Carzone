package com.carzone.service;

import com.carzone.dto.CarDto;
import com.carzone.model.Car;
import com.carzone.model.Company;
import com.carzone.repositoy.CarRepository;
import com.carzone.repositoy.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarService {

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private CompanyRepository companyRepository;

    public Car addCar(long id, Car car){
        Optional<Company> optional = companyRepository.findById(id);
        if (optional.isPresent()){
            Company company = optional.get();
            car.setCompany(company);
            return carRepository.save(car);
        }
        return null;
    }

    public List<Car> getAllCar(){
        return carRepository.findAll();
    }

    public Optional<Car> getCarById(long id){
        return carRepository.findById(id);
    }

    public CarDto updateCar(long id, CarDto carDto){
        Optional<Car> optional = carRepository.findById(id);
        if (optional.isPresent()){
            Car car = optional.get();
            car.setModel(carDto.getModel());
            car.setYear(carDto.getYear());
            carRepository.save(car);
        }
        return null;
    }

    public void deleteCar(long id){
        Optional<Car> optional = carRepository.findById(id);
        if (optional.isPresent()){
            Car car = optional.get();
            carRepository.delete(car);
        }
    }
}
