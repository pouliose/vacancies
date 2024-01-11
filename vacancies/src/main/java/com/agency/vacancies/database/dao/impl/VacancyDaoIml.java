package com.agency.vacancies.database.dao.impl;

import com.agency.vacancies.database.dao.VacancyDao;
import com.agency.vacancies.database.domain.Vacancy;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
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
        jdbcTemplate.update("INSERT INTO vacancies (id, company, title, announcedtime, company_id) VALUES (?,?,?,?,?)", vacancy.getId(), vacancy.getCompanyName(), vacancy.getTitle(), vacancy.getAnnouncedDateTime(), vacancy.getCompanyId());
    }

    @Override
    public Optional<Vacancy> findOne(Long id) {
        List<Vacancy> result = jdbcTemplate.query(
                "SELECT id, company, title, announcedtime, company_id FROM vacancies WHERE id=? LIMIT 1",
                new VacancyRowMapper(),
                id
        );
        return result.stream().findFirst();
    }

    public static class VacancyRowMapper implements RowMapper<Vacancy> {

        @Override
        public Vacancy mapRow(ResultSet rs, int rowNum) throws SQLException {
            return Vacancy.builder()
                    .id(rs.getLong("id"))
                    .companyName(rs.getString("company"))
                    .title(rs.getString("title"))
                    .announcedDateTime(rs.getTimestamp("announcedtime").toLocalDateTime())
                    .companyId(rs.getLong("company_id"))
                    .build();
        }
    }

    @Override
    public List<Vacancy> findAll() {
        return jdbcTemplate.query("SELECT id, company, title, announcedtime, company_id FROM vacancies",
                new VacancyRowMapper()
        );
    }

    @Override
    public void update(Long id, Vacancy vacancy) {
        jdbcTemplate.update("UPDATE vacancies SET id=?, company=?, title=?, announcedtime=?, company_id=? WHERE id=?", id, vacancy.getCompanyName(), vacancy.getTitle(), vacancy.getAnnouncedDateTime(), vacancy.getCompanyId(), id);
    }

    @Override
    public void delete(Long id) {
        jdbcTemplate.update("DELETE FROM vacancies WHERE id=?", id);
    }
}
