package com.agency.vacancies.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name="vacancies")
public class Vacancy {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "vacancy_id_seq")
    private Long id;
    private String companyName;
    private String title;
    private LocalDateTime announcedDateTime;

    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="author_id")
    private Company company;
}
