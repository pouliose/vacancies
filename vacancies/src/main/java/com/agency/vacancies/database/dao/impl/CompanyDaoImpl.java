package com.agency.vacancies.database.dao.impl;

import com.agency.vacancies.database.dao.CompanyDao;
import com.agency.vacancies.database.domain.Company;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
@Component
public class CompanyDaoImpl implements CompanyDao {
    private final JdbcTemplate jdbcTemplate;

    public CompanyDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void create(Company company) {
        jdbcTemplate.update("INSERT INTO companies(id, name) VALUES (?,?)", company.getId(), company.getName());
    }

    @Override
    public Optional<Company> findOne(Long id) {
        return Optional.empty();
    }

    @Override
    public List<Company> findAll() {
        return null;
    }

    @Override
    public void update(Long id, Company company) {

    }

    @Override
    public void delete(Long id) {

    }
}
