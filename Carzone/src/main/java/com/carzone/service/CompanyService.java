package com.carzone.service;

import com.carzone.dto.CarDto;
import com.carzone.dto.CompanyDto;
import com.carzone.dto.ResponseStructure;
import com.carzone.exception.CompanyAlreadyExists;
import com.carzone.exception.CompanyNotFound;
import com.carzone.model.Car;
import com.carzone.model.Company;
import com.carzone.repositoy.CarRepository;
import com.carzone.repositoy.CompanyRepository;
import com.carzone.service.serviceImpl.CompanyInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CompanyService implements CompanyInterface {

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private CarRepository carRepository;

    @Override
    public ResponseEntity<ResponseStructure<Company>> addCompany(CompanyDto companyDto) {
        Optional<Company> existingCompany = companyRepository.findByName(companyDto.getName());
        if (existingCompany.isPresent()) {
            throw new CompanyAlreadyExists("Company with name '" + companyDto.getName() + "' already exists.");
        }

        Company company = new Company();
        company.setName(companyDto.getName());
        company.setLocation(companyDto.getLocation());


        if (companyDto.getCars() != null) {
            List<Car> cars = new ArrayList<>();
            for (CarDto carDto : companyDto.getCars()) {
                Car car = new Car();
                car.setModel(carDto.getModel());
                car.setYear(carDto.getYear());
                car.setCompany(company);
                cars.add(car);
            }
            company.setCars(cars);
        }
        Company savedCompany = companyRepository.save(company);

        ResponseStructure<Company> responseStructure = new ResponseStructure<>(HttpStatus.OK.value(), "Company and cars saved successfully!", savedCompany);
        return new ResponseEntity<>(responseStructure, HttpStatus.OK);
    }


    @Override
    public ResponseEntity<ResponseStructure<List<Company>>> getAllCompany() {
        List<Company> companies = companyRepository.findAll();
        ResponseStructure<List<Company>> responseStructure = new ResponseStructure<List<Company>>(HttpStatus.OK.value(),"Show All Companies ",companies);
        return new ResponseEntity<ResponseStructure<List<Company>>>(responseStructure, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ResponseStructure<Company>> getCompanyById(long id){
        Optional<Company> optional = companyRepository.findById(id);
        if (optional.isPresent()){
            Company company = optional.get();
            ResponseStructure<Company> responseStructure = new ResponseStructure<Company>(HttpStatus.OK.value(),"Company Fetched Successfully.",company);
            return new ResponseEntity<ResponseStructure<Company>>(responseStructure, HttpStatus.OK);
        }else{
            throw new CompanyNotFound("Company with id "+id+" is not found.");
        }
    }

    @Override
    public ResponseEntity<ResponseStructure<Company>> getCompanyByName(String name){
        Optional<Company> optional = companyRepository.findByName(name);
        if (optional.isPresent()){
            Company company = optional.get();
            ResponseStructure<Company> responseStructure = new ResponseStructure<Company>(HttpStatus.OK.value(),"Company Fetched Successfully.",company);
            return new ResponseEntity<ResponseStructure<Company>>(responseStructure, HttpStatus.OK);
        }else{
            throw new CompanyNotFound("Company with name "+name+" is not found.");
        }
    }

    @Override
    public ResponseEntity<ResponseStructure<Company>> updateCompanyName(long id, CompanyDto companyDtores) {
        Optional<Company> optional = companyRepository.findById(id);

        if (optional.isPresent()) {
            Company company = optional.get();
            company.setName(companyDtores.getName());
            company.setLocation(companyDtores.getLocation());
            Company save = companyRepository.save(company);
            ResponseStructure<Company> responseStructure = new ResponseStructure<Company>(HttpStatus.OK.value(), "Company Updated Successfully.", save);
            return new ResponseEntity<ResponseStructure<Company>>(responseStructure, HttpStatus.OK);
        } else {
            throw new CompanyNotFound("Company with id "+id+" is not found.");
        }
    }

    @Override
    public ResponseEntity<ResponseStructure<CompanyDto>> deleteCompany(long id) {
        Optional<Company> optional = companyRepository.findById(id);
        if (optional.isPresent()) {
            Company company = optional.get();
            CompanyDto companyDto = new CompanyDto();
            companyDto.getName();
            companyDto.getLocation();
            companyDto.getCars();
            companyRepository.delete(company);

            ResponseStructure<CompanyDto> responseStructure = new ResponseStructure<>(HttpStatus.OK.value(), "Company Deleted Successfully.", companyDto);
            return new ResponseEntity<>(responseStructure, HttpStatus.OK);
        }else{
            throw new CompanyNotFound("Company with id "+id+" is not found.");
        }
    }

}