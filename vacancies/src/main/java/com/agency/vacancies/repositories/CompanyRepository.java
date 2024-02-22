package com.agency.vacancies.repositories;

import com.agency.vacancies.domain.entities.Company;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface CompanyRepository extends CrudRepository<Company, Long>, PagingAndSortingRepository<Company, Long> {
}
