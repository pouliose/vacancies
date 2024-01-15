package com.agency.vacancies.services.impl;

import com.agency.vacancies.domain.entities.Company;
import com.agency.vacancies.repositories.CompanyRepository;
import com.agency.vacancies.services.CompanyService;
import org.springframework.stereotype.Service;

@Service
public class CompanyServiceImpl implements CompanyService {

    CompanyRepository companyRepository;

    public CompanyServiceImpl(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @Override
    public Company createCompany(Company company) {
        return companyRepository.save(company);
    }
}
