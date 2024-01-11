package com.agency.vacancies.database.dao;

import com.agency.vacancies.database.domain.Vacancy;

import java.util.List;
import java.util.Optional;

public interface VacancyDao {
    void create(Vacancy vacancy);

    Optional<Vacancy> findOne(Long id);

    List<Vacancy> findAll();

    void update(Long id, Vacancy vacancy);

    void delete(Long id);
}
