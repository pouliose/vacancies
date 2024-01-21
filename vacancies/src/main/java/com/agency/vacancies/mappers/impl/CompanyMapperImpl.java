package com.agency.vacancies.mappers.impl;

import com.agency.vacancies.domain.dto.CompanyDto;
import com.agency.vacancies.domain.entities.Company;
import com.agency.vacancies.mappers.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class CompanyMapperImpl implements Mapper<Company, CompanyDto> {
    private ModelMapper modelMapper;

    public CompanyMapperImpl(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public CompanyDto mapTo(Company company) {
        return modelMapper.map(company, CompanyDto.class);
    }

    @Override
    public Company mapFrom(CompanyDto companyDto) {
        return modelMapper.map(companyDto, Company.class);
    }
}
