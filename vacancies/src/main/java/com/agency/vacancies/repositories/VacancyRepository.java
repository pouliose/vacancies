package com.agency.vacancies.repositories;

import com.agency.vacancies.entites.Vacancy;
import org.springframework.data.repository.CrudRepository;

public interface VacancyRepository extends CrudRepository<Vacancy, Long> {
}
