package com.agency.vacancies.services;

import com.agency.vacancies.domain.entities.Company;

import java.util.List;

public interface CompanyService {
    Company createCompany(Company company);

    List<Company> findAll();
}
