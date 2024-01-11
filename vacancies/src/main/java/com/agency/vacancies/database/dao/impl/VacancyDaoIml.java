package com.agency.vacancies.database.dao.impl;

import com.agency.vacancies.database.dao.VacancyDao;
import com.agency.vacancies.database.domain.Vacancy;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class VacancyDaoIml implements VacancyDao {

    private final JdbcTemplate jdbcTemplate;

    public VacancyDaoIml(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void create(Vacancy vacancy) {
        jdbcTemplate.update("INSERT INTO vacancies (id, company, title, dateTime) VALUES (?,?,?,?)", vacancy.getId(), vacancy.getCompanyName(), vacancy.getTitle(), vacancy.getAnnouncedDateTime());
    }

    @Override
    public Optional<Vacancy> findOne(Long id) {
        return Optional.empty();
    }

    @Override
    public List<Vacancy> findAll() {
        return null;
    }

    @Override
    public void update(Long id, Vacancy vacancy) {
        jdbcTemplate.update("UPDATE vacancies SET id=?, company=?, title=?, announcedDate=? WHERE id=?", id, vacancy.getCompanyName(), vacancy.getTitle(), vacancy.getAnnouncedDateTime(), id);
    }

    @Override
    public void delete(Long id) {
        jdbcTemplate.update("DELETE FROM vacancies WHERE id=?", id);
    }
}
