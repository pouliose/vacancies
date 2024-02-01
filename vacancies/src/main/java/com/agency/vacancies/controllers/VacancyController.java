package com.agency.vacancies.controllers;

import com.agency.vacancies.domain.dto.VacancyDto;
import com.agency.vacancies.domain.entities.Vacancy;
import com.agency.vacancies.mappers.Mapper;
import com.agency.vacancies.services.VacancyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/api")
public class VacancyController {
    private Mapper<Vacancy, VacancyDto> vacancyMapper;
    private VacancyService vacancyService;

    public VacancyController(Mapper<Vacancy, VacancyDto> vacancyMapper, VacancyService vacancyService) {
        this.vacancyMapper = vacancyMapper;
        this.vacancyService = vacancyService;
    }

    @PostMapping(path = "/vacancies")
    public ResponseEntity<VacancyDto> createVacancy(@RequestBody VacancyDto vacancyDto) {
        Vacancy vacancy = vacancyMapper.mapFrom(vacancyDto);
        Vacancy vacancySaved = vacancyService.createVacancy(vacancy);
        VacancyDto vacancyDtoSaved = vacancyMapper.mapTo(vacancySaved);
        return new ResponseEntity<>(vacancyDtoSaved, HttpStatus.CREATED);
    }

    @PutMapping(path = "/vacancies/{id}")
    public ResponseEntity<VacancyDto> updateVacancy(
            @PathVariable("id") Long id,
            @RequestBody VacancyDto vacancyDto) {
        Vacancy vacancy = vacancyMapper.mapFrom(vacancyDto);
        Vacancy vacancySaved = vacancyService.updateVacancy(vacancy);
        VacancyDto vacancyDtoSaved = vacancyMapper.mapTo(vacancySaved);
        return new ResponseEntity<>(vacancyDtoSaved, HttpStatus.OK);
    }

    @GetMapping(path = "/vacancies")
    public List<VacancyDto> getListVacancies() {
        List<Vacancy> vacancies = vacancyService.findAll();

        return vacancies.stream()
                .map(vacancyMapper::mapTo)
                .collect(Collectors.toList());
    }
}
