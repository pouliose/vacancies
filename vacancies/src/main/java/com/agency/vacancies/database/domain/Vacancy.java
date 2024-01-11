package com.agency.vacancies.database.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Vacancy {
    private Long id;
    private String companyName;
    private String title;
    private LocalDateTime announcedDateTime;

}
