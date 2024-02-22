package com.agency.vacancies.services.impl;

import com.agency.vacancies.domain.entities.Vacancy;

import com.agency.vacancies.repositories.VacancyRepository;
import com.agency.vacancies.services.VacancyService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
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

    @Override
    public Page<Vacancy> findAll(Pageable pageable) {
        return vacancyRepository.findAll(pageable);
    }

    @Override
    public boolean isExists(long id) {
        return vacancyRepository.existsById(id);
    }

    @Override
    public Vacancy partialUpdateVacancy(Long id, Vacancy vacancy) {
        vacancy.setId(id);

        return vacancyRepository.findById(id).map((existing) -> {
            Optional.ofNullable(vacancy.getTitle()).ifPresent(existing::setTitle);
            Optional.ofNullable(vacancy.getAnnouncedDateTime()).ifPresent(existing::setAnnouncedDateTime);
            return vacancyRepository.save(existing);
        }).orElseThrow(()-> new RuntimeException("Vacancy does not exist"));
    }

    @Override
    public void delete(Long id) {
        vacancyRepository.deleteById(id);
    }

}
