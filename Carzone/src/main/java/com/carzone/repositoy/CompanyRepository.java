package com.carzone.repositoy;

import com.carzone.model.Car;
import com.carzone.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface CompanyRepository extends JpaRepository<Company,Long> {

    public void deleteByName(String name);
}
