package com.agency.vacancies.repositories;

import com.agency.vacancies.CreateTestDataUtil;
import com.agency.vacancies.entites.Company;
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
public class CompanyRepositoryIntegrationTests {
    private final CompanyRepository underTest;

    @Autowired
    public CompanyRepositoryIntegrationTests(CompanyRepository underTest) {
        this.underTest = underTest;
    }

    @Test
    public void testThatVacancyCanBeCreatedAndRecalled() {
        Company company = CreateTestDataUtil.createTestCompanyA();
        underTest.save(company);
        Optional<Company> result = underTest.findById(company.getId());
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(company);
    }

    @Test
    public void testThatMultipleCompaniesCanBeCreatedAndRecalled() {
        Company companyA = CreateTestDataUtil.createTestCompanyA();
        underTest.save(companyA);

        Company companyB = CreateTestDataUtil.createTestCompanyB();
        underTest.save(companyB);

        Company companyC = CreateTestDataUtil.createTestCompanyC();
        underTest.save(companyC);

        Iterable<Company> result = underTest.findAll();
        assertThat(result)
                .hasSize(3)
                .containsExactlyInAnyOrder(companyA, companyB, companyC);
    }

    @Test
    public void testThatCompanyCanBeUpdated() {
        Company company = CreateTestDataUtil.createTestCompanyA();
        underTest.save(company);

        company.setName("NEWVALUE");
        underTest.save(company);

        Optional<Company> result = underTest.findById(company.getId());
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(company);
    }

    @Test
    public void testThatCompanyCanBeDeleted() {
        Company company = CreateTestDataUtil.createTestCompanyA();
        underTest.save(company);
        underTest.deleteById(company.getId());
        Optional<Company> result = underTest.findById(company.getId());
        assertThat(result).isEmpty();
    }

}
