package com.agency.vacancies;

import com.agency.vacancies.database.domain.Company;
import com.agency.vacancies.database.domain.Vacancy;

import java.time.LocalDateTime;

public class CreateTestDataUtil {

        public static Company createTestCompanyA(){
            return Company.builder()
                    .id(1L)
                    .name("Google")
                    .build();
        }

        public static Vacancy createTestVacancyA(){
            return Vacancy.builder()
                    .id(1L)
                    .companyName("Google")
                    .title("Data Engineer")
                    .announcedDateTime(LocalDateTime.of(2023,12,31,8,30))
                    .build();
        }

    public static Vacancy createTestVacancyB(){
        return Vacancy.builder()
                .id(2L)
                .companyName("Twilio")
                .title("Software Engineer")
                .announcedDateTime(LocalDateTime.of(2023,12,1,8,30))
                .build();
    }
}
