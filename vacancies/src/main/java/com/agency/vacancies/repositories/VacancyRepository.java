package com.agency.vacancies.repositories;

import com.agency.vacancies.domain.Vacancy;
import org.springframework.data.repository.CrudRepository;

public interface VacancyRepository extends CrudRepository<Vacancy, Long> {
}