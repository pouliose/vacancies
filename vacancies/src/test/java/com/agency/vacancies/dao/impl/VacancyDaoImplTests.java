package com.agency.vacancies.dao.impl;

import com.agency.vacancies.CreateTestDataUtil;
import com.agency.vacancies.database.dao.impl.VacancyDaoIml;
import com.agency.vacancies.database.domain.Vacancy;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.JdbcTemplate;

import java.time.LocalDateTime;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class VacancyDaoImplTests {
    @Mock
    private JdbcTemplate jdbcTemplate;
    @InjectMocks
    private VacancyDaoIml underTest;

    @Test
    public void testThatCreateVacancyGeneratesCorrectSQL() {
        Vacancy vacancy = CreateTestDataUtil.createTestVacancyA();
        underTest.create(vacancy);
        verify(jdbcTemplate).update(eq("INSERT INTO vacancies (id, company, title, announcedtime, company_id) VALUES (?,?,?,?,?)"), eq(1L), eq("Google"), eq("Data Engineer"), eq(LocalDateTime.of(2023, 12, 31, 8, 30)), eq(1L));
    }

    @Test
    public void testThatUpdateGeneratesCorrectSql() {
        Vacancy vacancy = CreateTestDataUtil.createTestVacancyB();
        underTest.update(2L, vacancy);
        verify(jdbcTemplate).update("UPDATE vacancies SET id=?, company=?, title=?, announcedtime=?, company_id=? WHERE id=?", 2L, "Twilio", "Software Engineer", LocalDateTime.of(2023, 12, 1, 8, 30), 2L, 2L);
    }

    @Test
    public void testThatDeleteGeneratesCorrectSql() {
        underTest.delete(1L);
        verify(jdbcTemplate).update("DELETE FROM vacancies WHERE id=?", 1L);
    }
}
