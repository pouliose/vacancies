package com.agency.vacancies.repositories;

import com.agency.vacancies.CreateTestDataUtil;
import com.agency.vacancies.entites.Company;
import com.agency.vacancies.entites.Vacancy;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class VacancyRepositoryIntegrationTests {
    private final VacancyRepository underTest;

    @Autowired
    public VacancyRepositoryIntegrationTests(VacancyRepository underTest) {
        this.underTest = underTest;
    }

    @Test
    public void testThatVacancyCanBeCreatedAndRecalled() {
        Company company = CreateTestDataUtil.createTestCompanyA();
        Vacancy vacancy = CreateTestDataUtil.createTestVacancyA(company);
        vacancy.setCompany(company);
        underTest.save(vacancy);
        Optional<Vacancy> result = underTest.findById(company.getId());
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(vacancy);
    }

    @Test
    public void testThatMultipleVacanciesCanBeCreatedAndRecalled() {
        Company company = CreateTestDataUtil.createTestCompanyA();

        Vacancy vacancyA = CreateTestDataUtil.createTestVacancyA(company);
        Vacancy vacancyB = CreateTestDataUtil.createTestVacancyB(company);

        underTest.save(vacancyA);
        underTest.save(vacancyB);

        Iterable<Vacancy> result = underTest.findAll();
        result.forEach(System.out::println);
        assertThat(result).hasSize(2).containsExactlyInAnyOrder(vacancyA, vacancyB);
    }

    @Test
    public void testThatVacancyCanBeUpdated() {
        Company company = CreateTestDataUtil.createTestCompanyA();
        Vacancy vacancy = CreateTestDataUtil.createTestVacancyA(company);
        underTest.save(vacancy);

        vacancy.setTitle("NEWVALUE");
        underTest.save(vacancy);

        Optional<Vacancy> result = underTest.findById(vacancy.getId());
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(vacancy);
    }

    @Test
    public void testThatGetVacanciesWithAnnouncedDateTimePreviousThan() {
        Company company = CreateTestDataUtil.createTestCompanyA();

        Vacancy vacancyA = CreateTestDataUtil.createTestVacancyA(company);
        underTest.save(vacancyA);
        Vacancy vacancyB = CreateTestDataUtil.createTestVacancyB(company);
        underTest.save(vacancyB);
        Vacancy vacancyC = CreateTestDataUtil.createTestVacancyC(company);
        underTest.save(vacancyC);

        Iterable<Vacancy> result = underTest.announcedDateTimeLessThan(LocalDateTime.of(2024, 1, 13, 8, 30));
        assertThat(result).containsExactlyInAnyOrder(vacancyA, vacancyB);
    }
}
