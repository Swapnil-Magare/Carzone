package com.carzone.service;

import com.carzone.dto.CompanyDto;
import com.carzone.model.Company;
import com.carzone.repositoy.CarRepository;
import com.carzone.repositoy.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyService {

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private CarRepository carRepository;

    public void addCompany(Company company){
        companyRepository.save(company);
    }


    public List<Company> getAllCompany() {
        return companyRepository.findAll();
    }

    public Optional<Company> getCompanyById(long id){
        return companyRepository.findById(id);
    }

    public void deleteCompany(long id){
        Optional<Company> optional = companyRepository.findById(id);
        if (optional.isPresent()) {
            Company company = optional.get();
            companyRepository.delete(company);
        }
    }

    public CompanyDto updateCompanyName(long id, CompanyDto companyDtores){
        Optional<Company> optional = companyRepository.findById(id);
        if (optional.isPresent()){
            Company company = optional.get();
            company.setName(companyDtores.getName());
            company.setLocation(companyDtores.getLocation());
            companyRepository.save(company);
        }
        return null;
    }
}
