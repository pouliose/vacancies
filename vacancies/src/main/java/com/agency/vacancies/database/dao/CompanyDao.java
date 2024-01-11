package com.agency.vacancies.database.dao;

import com.agency.vacancies.database.domain.Company;

import java.util.List;
import java.util.Optional;

public interface CompanyDao {
    void create(Company company);

    Optional<Company> findOne(Long id);

    List<Company> findAll();

    void update(Long id, Company company);

    void delete(Long id);
}
