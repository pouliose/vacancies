package com.agency.vacancies.dao.impl;

import com.agency.vacancies.CreateTestDataUtil;
import com.agency.vacancies.database.dao.CompanyDao;
import com.agency.vacancies.database.dao.impl.CompanyDaoImpl;
import com.agency.vacancies.database.dao.impl.VacancyDaoIml;
import com.agency.vacancies.database.domain.Company;
import com.agency.vacancies.database.domain.Vacancy;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class VacancyDaoImplIntegrationTests {
    private final CompanyDaoImpl companyDao;
    private final VacancyDaoIml underTest;

    @Autowired
    public VacancyDaoImplIntegrationTests(CompanyDaoImpl companyDao, VacancyDaoIml underTest) {
        this.companyDao = companyDao;
        this.underTest = underTest;
    }

    @Test
    public void testThatVacancyCanBeCreatedAndRecalled() {
        Company company = CreateTestDataUtil.createTestCompanyA();
        companyDao.create(company);
        Vacancy vacancy = CreateTestDataUtil.createTestVacancyA();
        vacancy.setCompanyId(company.getId());
        underTest.create(vacancy);
        Optional<Vacancy> result = underTest.findOne(company.getId());
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(vacancy);
    }

    @Test
    public void testThatMultipleVacanciesCanBeCreatedAndRecalled() {
        Company company = CreateTestDataUtil.createTestCompanyA();
        companyDao.create(company);

        Vacancy vacancyA = CreateTestDataUtil.createTestVacancyA();
        vacancyA.setCompanyId(company.getId());
        underTest.create(vacancyA);

        Vacancy vacancyB = CreateTestDataUtil.createTestVacancyB();
        vacancyB.setCompanyId(company.getId());
        underTest.create(vacancyB);

        List<Vacancy> result = underTest.findAll();
        assertThat(result).hasSize(2).containsExactly(vacancyA, vacancyB);
    }

    public void testThatVacancyCanBeUpdated(){
        Company company = CreateTestDataUtil.createTestCompanyA();
        companyDao.create(company);

        Vacancy vacancyA = CreateTestDataUtil.createTestVacancyA();
        vacancyA.setCompanyId(company.getId());
        underTest.create(vacancyA);

        vacancyA.setTitle("Data Warehouse Engineer");
        underTest.update(vacancyA.getId(),vacancyA);

        Optional<Vacancy> result = underTest.findOne(vacancyA.getId());
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(vacancyA);

    }
}
