package com.agency.vacancies;

import com.agency.vacancies.domain.dto.CompanyDto;
import com.agency.vacancies.domain.dto.VacancyDto;
import com.agency.vacancies.domain.entities.Company;
import com.agency.vacancies.domain.entities.Vacancy;

import java.time.LocalDateTime;

public class CreateTestDataUtil {

        public static Company createTestCompanyA(){
            return Company.builder()
                    .id(1L)
                    .name("Google")
                    .build();
        }

    public static Company createTestCompanyB(){
        return Company.builder()
                .id(2L)
                .name("Syntax")
                .build();
    }

    public static Company createTestCompanyC(){
        return Company.builder()
                .id(3L)
                .name("Cisco")
                .build();
    }

    public static Vacancy createTestVacancyA(final Company company){
        return Vacancy.builder()
                .id(1L)
                .title("Data Engineer")
                .announcedDateTime(LocalDateTime.of(2023,12,31,8,30, 25))
                .company(company)
                .build();
    }

    public static VacancyDto createTestVacancyDtoA(final CompanyDto company){
        return VacancyDto.builder()
                .id(1L)
                .title("Data Engineer")
                .announcedDateTime(LocalDateTime.of(2023,12,31,8,30, 25))
                .company(company)
                .build();
    }

    public static Vacancy createTestVacancyB(final Company company){
        return Vacancy.builder()
                .id(2L)
                .title("Software Engineer")
                .announcedDateTime(LocalDateTime.of(2023,12,1,8,30, 25))
                .company(company)
                .build();
    }

    public static Vacancy createTestVacancyC(final Company company){
        return Vacancy.builder()
                .id(3L)
                .title("Software Engineer Level II")
                .announcedDateTime(LocalDateTime.of(2024,1,14,8,30, 25))
                .company(company)
                .build();
    }
}
