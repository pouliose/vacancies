package com.agency.vacancies.repositories;

import com.agency.vacancies.entites.Company;
import org.springframework.data.repository.CrudRepository;

public interface CompanyRepository extends CrudRepository<Company, Long> {
}
