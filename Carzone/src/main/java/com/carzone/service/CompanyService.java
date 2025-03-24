package com.carzone.service;

//import com.carzone.controller.CompanyDto;
import com.carzone.dto.CompanyDto;
import com.carzone.model.Company;
import com.carzone.repositoy.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyService {

    @Autowired
    private CompanyRepository companyRepository;

    public Company addCompany(Company company) {
        return companyRepository.save(company);
    }

    public List<Company> getAllCompany() {
        return companyRepository.findAll();
    }

    public Optional<Company> getByCompanyId(Long id) {
        return companyRepository.findById(id);
    }

    public void deleteByCompanyid(Long id) {
        Optional<Company> optional = companyRepository.findById(id);
        if (optional.isPresent()) {
            Company company = optional.get();
            companyRepository.delete(company);
        }
    }

    public CompanyDto updateById(Long id, CompanyDto companyDto) {
        Optional<Company> optionalCompany = companyRepository.findById(id);
        if (optionalCompany.isPresent()) {
            Company company = optionalCompany.get();
            company.setName(companyDto.getName());
            company.setLocation(companyDto.getLocation());
            companyRepository.save(company);
    }
        return null;
}






}