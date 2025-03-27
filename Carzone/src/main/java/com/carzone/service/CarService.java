package com.carzone.service;

import com.carzone.dto.CarDto;
import com.carzone.dto.ResponseStructure;
import com.carzone.exception.CarAlreadyExists;
import com.carzone.exception.CarNotFound;
import com.carzone.model.Car;
import com.carzone.model.Company;
import com.carzone.repositoy.CarRepository;
import com.carzone.repositoy.CompanyRepository;
import com.carzone.service.serviceImpl.CarInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarService implements CarInterface {
    @Autowired
    private CarRepository carRepository;

    @Autowired
    private CompanyRepository companyRepository;

    @Override
    public ResponseEntity<ResponseStructure<CarDto>> addCar(CarDto carDto) {
        Optional<Company> companyOpt = companyRepository.findById(carDto.getCompanyId());
        Company company = companyOpt.get();
        Optional<Car> existingCar = carRepository.findByModelAndCompanyId(carDto.getModel(), company.getId());
        if (existingCar.isPresent()) {
            throw new CarAlreadyExists("Car with model " + carDto.getModel() + " already exists for company " + company.getName());
        }
        Car car = new Car();
        car.setModel(carDto.getModel());
        car.setYear(carDto.getYear());
        car.setCompany(company);
        Car save = carRepository.save(car);
        ResponseStructure rs = new ResponseStructure<Car>(HttpStatus.CREATED.value(), "Car Added Successfully!", save);
        return new ResponseEntity<ResponseStructure<CarDto>>(rs, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<ResponseStructure<CarDto>> getBycarId(Long id) {
        Optional<Car> byId = carRepository.findById(id);
        if (byId.isEmpty()) {
            throw new CarNotFound("Car with this credentials is not available!");
        }
        ResponseStructure rs = new ResponseStructure<Optional<Car>>(HttpStatus.CREATED.value(), "Car Fetched Successfully!", byId);
        return new ResponseEntity<ResponseStructure<CarDto>>(rs, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<ResponseStructure<CarDto>> getByCarModel(String model) {
        Optional<Car> carOpt = carRepository.findByModel(model);
        if (carOpt.isEmpty()) {
            throw new CarNotFound("Model not found!");
        }
        ResponseStructure rs = new ResponseStructure<Optional<Car>>(HttpStatus.CREATED.value(), "Car Fetched Successfully!", carOpt);
        return new ResponseEntity<ResponseStructure<CarDto>>(rs, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<ResponseStructure<List<CarDto>>> getAllCar(int pageNumber, int pageSize) {
        Pageable p = PageRequest.of(pageNumber, pageSize);
        Page<Car> carPage = carRepository.findAll(p);
        List<Car> allCars = carPage.getContent();
        if (allCars.isEmpty()) {
            throw new CarNotFound("No data Found!");
        }
        ResponseStructure rs = new ResponseStructure<List<Car>>(HttpStatus.CREATED.value(), "All Car Fetched Successfully!", allCars);
        return new ResponseEntity<ResponseStructure<List<CarDto>>>(rs, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<ResponseStructure<CarDto>> updateCar(long id, CarDto carDto) {
        Optional<Car> optional = carRepository.findById(id);
        if (optional.isPresent()) {
            Car car = optional.get();
            car.setModel(carDto.getModel());
            car.setYear(carDto.getYear());
            Car save = carRepository.save(car);
            ResponseStructure rs = new ResponseStructure<Car>(HttpStatus.OK.value(), "Car Details Updated Successfully!", save);
            return new ResponseEntity<ResponseStructure<CarDto>>(rs, HttpStatus.OK);
        }
        throw new CarNotFound("Company not found for this Car");
    }

    @Override
    public ResponseEntity<ResponseStructure<Optional<Car>>> deleteByCarId(Long id) {
        Optional<Car> carOptional = carRepository.findById(id);

        if (carOptional.isPresent()) {
            carRepository.deleteById(id);
            ResponseStructure<Optional<Car>> response = new ResponseStructure<>(
                    HttpStatus.OK.value(),
                    "Car deleted successfully!",
                    carOptional
            );
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        throw new CarNotFound("Failed to Delete");
    }
}