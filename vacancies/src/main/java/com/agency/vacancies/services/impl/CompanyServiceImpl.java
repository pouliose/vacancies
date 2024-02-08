package com.agency.vacancies.services.impl;

import com.agency.vacancies.domain.entities.Company;
import com.agency.vacancies.repositories.CompanyRepository;
import com.agency.vacancies.services.CompanyService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class CompanyServiceImpl implements CompanyService {

    CompanyRepository companyRepository;

    public CompanyServiceImpl(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @Override
    public Company saveCompany(Company company) {
        return companyRepository.save(company);
    }

    @Override
    public List<Company> findAll() {
        return StreamSupport
                .stream(
                        companyRepository.findAll().spliterator(),
                        false)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Company> findOne(Long id) {
        return companyRepository.findById(id);
    }

    @Override
    public boolean isExists(Long id) {
        return companyRepository.existsById(id);
    }

    @Override
    public void delete(Long id) {
        companyRepository.deleteById(id);
    }
}
