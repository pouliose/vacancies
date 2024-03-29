package com.agency.vacancies.domain.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
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
@JsonIgnoreProperties(ignoreUnknown = true)
@Table(name="vacancies")
public class Vacancy {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "vacancy_id_seq")
    private Long id;
    private String title;
    @JsonProperty("announcedTime")
    private LocalDateTime announcedDateTime;

    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="company_id")
    private Company company;
}
