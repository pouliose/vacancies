package com.agency.vacancies.mappers.impl;

import com.agency.vacancies.domain.dto.VacancyDto;
import com.agency.vacancies.domain.entities.Vacancy;
import com.agency.vacancies.mappers.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class VacancyMapperImpl implements Mapper<Vacancy, VacancyDto> {
    private ModelMapper modelMapper;

    public VacancyMapperImpl(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public VacancyDto mapTo(Vacancy vacancy) {
        return modelMapper.map(vacancy, VacancyDto.class);
    }

    @Override
    public Vacancy mapFrom(VacancyDto vacancyDto) {
        return modelMapper.map(vacancyDto, Vacancy.class);
    }
}
