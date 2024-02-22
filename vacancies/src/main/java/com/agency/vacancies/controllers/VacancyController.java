package com.agency.vacancies.controllers;

import com.agency.vacancies.domain.dto.VacancyDto;
import com.agency.vacancies.domain.entities.Vacancy;
import com.agency.vacancies.mappers.Mapper;
import com.agency.vacancies.services.VacancyService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

        if(!vacancyService.isExists(id)){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        vacancyDto.setId(id);
        Vacancy vacancy = vacancyMapper.mapFrom(vacancyDto);
        Vacancy vacancySaved = vacancyService.updateVacancy(vacancy);
        VacancyDto vacancyDtoSaved = vacancyMapper.mapTo(vacancySaved);
        return new ResponseEntity<>(vacancyDtoSaved, HttpStatus.OK);
    }

    @PatchMapping(path="/vacancies/{id}")
    public ResponseEntity<VacancyDto> partialUpdateVacancy(@PathVariable("id") Long id, @RequestBody VacancyDto vacancyDto) {
        if(!vacancyService.isExists(id)){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Vacancy vacancy = vacancyMapper.mapFrom(vacancyDto);
        Vacancy vacancyUpdated = vacancyService.partialUpdateVacancy(id, vacancy);

        return new ResponseEntity<>(vacancyMapper.mapTo(vacancyUpdated), HttpStatus.OK);

    }

    @GetMapping(path = "/vacancies")
    public Page<VacancyDto> getListVacancies(Pageable pageable) {
        Page<Vacancy> vacancies = vacancyService.findAll(pageable);
        return vacancies.map(vacancyMapper::mapTo);
    }

    @DeleteMapping(path = "/vacancies/{id}")
    public ResponseEntity deleteVacancy(@PathVariable("id") Long id) {
       vacancyService.delete(id);

       return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
