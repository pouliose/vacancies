package com.agency.vacancies.services;

import com.agency.vacancies.domain.entities.Vacancy;

import java.util.List;

public interface VacancyService {
    Vacancy createVacancy(Vacancy vacancy);

    Vacancy updateVacancy(Vacancy vacancy);

    List<Vacancy> findAll();
}
