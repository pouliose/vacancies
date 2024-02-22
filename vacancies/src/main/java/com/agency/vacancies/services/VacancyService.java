package com.agency.vacancies.services;

import com.agency.vacancies.domain.entities.Vacancy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface VacancyService {
    Vacancy createVacancy(Vacancy vacancy);

    Vacancy updateVacancy(Vacancy vacancy);

    List<Vacancy> findAll();
    Page<Vacancy> findAll(Pageable pageable);

    boolean isExists(long id);

    Vacancy partialUpdateVacancy(Long id, Vacancy vacancy);

    void delete(Long id);
}
