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

    public Car addCar(CarDto carDto) {
        Optional<Company> companyOpt = companyRepository.findById(carDto.getCompanyId());

        if (companyOpt.isEmpty()) {
            throw new RuntimeException("Company not found for ID: " + carDto.getCompanyId());
        }

        Company company = companyOpt.get();
        Car car = new Car();
        car.setModel(carDto.getModel());
        car.setYear(carDto.getYear());
        car.setCompany(company);
        return carRepository.save(car);
    }

    public Optional<Car> getBycarId(Long id) {
        return carRepository.findById(id);
    }

    public List<Car> getAllCar() {
        return carRepository.findAll();
    }


    public CarDto updateCar(long id, CarDto carDto) {
        Optional<Car> optional = carRepository.findById(id);
        if (optional.isPresent()) {
            Car car = optional.get();
            car.setModel(carDto.getModel());
            car.setYear(carDto.getYear());
            carRepository.save(car);
        }
        return null;
    }
    public void deleteByCarId(Long id) {
        carRepository.deleteById(id);
    }
}
