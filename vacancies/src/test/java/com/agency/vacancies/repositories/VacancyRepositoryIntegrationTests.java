package com.agency.vacancies.repositories;

import com.agency.vacancies.CreateTestDataUtil;
import com.agency.vacancies.domain.Company;
import com.agency.vacancies.domain.Vacancy;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class VacancyRepositoryIntegrationTests {
    private final CompanyRepository companyRepository;
    private final VacancyRepository underTest;

    @Autowired
    public VacancyRepositoryIntegrationTests(CompanyRepository companyRepository, VacancyRepository underTest) {
        this.companyRepository = companyRepository;
        this.underTest = underTest;
    }

    @Test
    public void testThatVacancyCanBeCreatedAndRecalled() {
        Company company = CreateTestDataUtil.createTestCompanyA();
        companyRepository.save(company);
        Vacancy vacancy = CreateTestDataUtil.createTestVacancyA(company);
        vacancy.setCompany(company);
        underTest.save(vacancy);
        Optional<Vacancy> result = underTest.findById(company.getId());
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(vacancy);
    }

//    @Test
//    public void testThatMultipleVacanciesCanBeCreatedAndRecalled() {
//        Company company = CreateTestDataUtil.createTestCompanyA();
//        companyDao.create(company);
//
//        Vacancy vacancyA = CreateTestDataUtil.createTestVacancyA();
//        vacancyA.setCompanyId(company.getId());
//        underTest.create(vacancyA);
//
//        Vacancy vacancyB = CreateTestDataUtil.createTestVacancyB();
//        vacancyB.setCompanyId(company.getId());
//        underTest.create(vacancyB);
//
//        List<Vacancy> result = underTest.findAll();
//        assertThat(result).hasSize(2).containsExactly(vacancyA, vacancyB);
//    }
//
//    public void testThatVacancyCanBeUpdated(){
//        Company company = CreateTestDataUtil.createTestCompanyA();
//        companyDao.create(company);
//
//        Vacancy vacancyA = CreateTestDataUtil.createTestVacancyA();
//        vacancyA.setCompanyId(company.getId());
//        underTest.create(vacancyA);
//
//        vacancyA.setTitle("Data Warehouse Engineer");
//        underTest.update(vacancyA.getId(),vacancyA);
//
//        Optional<Vacancy> result = underTest.findOne(vacancyA.getId());
//        assertThat(result).isPresent();
//        assertThat(result.get()).isEqualTo(vacancyA);
//
//    }
}
