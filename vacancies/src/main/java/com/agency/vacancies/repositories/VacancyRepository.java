package com.agency.vacancies.repositories;

import com.agency.vacancies.entites.Vacancy;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDateTime;

public interface VacancyRepository extends CrudRepository<Vacancy, Long> {
    Iterable<Vacancy> announcedDateTimeLessThan(LocalDateTime dateTime);
}
