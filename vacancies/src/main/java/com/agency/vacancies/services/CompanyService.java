package com.agency.vacancies.services;

import com.agency.vacancies.domain.entities.Company;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface CompanyService {
    Company saveCompany(Company company);

    List<Company> findAll();

    Page<Company> findAll(Pageable pageable);

    Optional<Company> findOne(Long id);

    boolean isExists(Long id);

    void delete(Long id);
}
