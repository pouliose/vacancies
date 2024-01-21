package com.agency.vacancies.services;

import com.agency.vacancies.domain.entities.Vacancy;

public interface VacancyService {
    Vacancy createVacancy(Vacancy vacancy);

    Vacancy updateVacancy(Vacancy vacancy);
}
