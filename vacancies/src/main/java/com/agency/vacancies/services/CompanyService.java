package com.agency.vacancies.services;

import com.agency.vacancies.domain.entities.Company;

import java.util.List;
import java.util.Optional;

public interface CompanyService {
    Company saveCompany(Company company);

    List<Company> findAll();

    Optional<Company> findOne(Long id);

    boolean isExists(Long id);

    void delete(Long id);
}
