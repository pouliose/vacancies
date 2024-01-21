package com.agency.vacancies.services.impl;

import com.agency.vacancies.domain.entities.Company;
import com.agency.vacancies.domain.entities.Vacancy;
import com.agency.vacancies.repositories.CompanyRepository;
import com.agency.vacancies.repositories.VacancyRepository;
import com.agency.vacancies.services.VacancyService;
import org.springframework.stereotype.Service;

@Service
public class VacancyServiceImpl implements VacancyService {

    VacancyRepository vacancyRepository;

    public VacancyServiceImpl(VacancyRepository vacancyRepository) {
        this.vacancyRepository = vacancyRepository;
    }

    @Override
    public Vacancy createVacancy(Vacancy vacancy) {
        return vacancyRepository.save(vacancy);
    }

    @Override
    public Vacancy updateVacancy(Vacancy vacancy) {
        return vacancyRepository.save(vacancy);
    }
}
