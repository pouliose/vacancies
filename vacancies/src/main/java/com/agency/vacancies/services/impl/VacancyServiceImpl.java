package com.agency.vacancies.services.impl;

import com.agency.vacancies.domain.dto.VacancyDto;
import com.agency.vacancies.domain.entities.Company;
import com.agency.vacancies.domain.entities.Vacancy;
import com.agency.vacancies.repositories.CompanyRepository;
import com.agency.vacancies.repositories.VacancyRepository;
import com.agency.vacancies.services.VacancyService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class VacancyServiceImpl implements VacancyService {

    VacancyRepository vacancyRepository;
    private final ModelMapper modelMapper;

    public VacancyServiceImpl(VacancyRepository vacancyRepository,
                              ModelMapper modelMapper) {
        this.vacancyRepository = vacancyRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Vacancy createVacancy(Vacancy vacancy) {
        return vacancyRepository.save(vacancy);
    }

    @Override
    public Vacancy updateVacancy(Vacancy vacancy) {
        return vacancyRepository.save(vacancy);
    }

    @Override
    public List<Vacancy> findAll() {
        return StreamSupport
                .stream(
                        vacancyRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }
}
