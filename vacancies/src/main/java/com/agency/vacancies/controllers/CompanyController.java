package com.agency.vacancies.controllers;

import com.agency.vacancies.domain.dto.CompanyDto;
import com.agency.vacancies.domain.entities.Company;
import com.agency.vacancies.mappers.Mapper;
import com.agency.vacancies.services.CompanyService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CompanyController {
    private CompanyService companyService;
    private Mapper<Company, CompanyDto> companyMapper;

    public CompanyController(CompanyService companyService, Mapper<Company, CompanyDto> companyMapper) {
        this.companyService = companyService;
        this.companyMapper = companyMapper;
    }

    @GetMapping(path="/")
    public String welcome(){
        return "Welcome";
    }

    @PostMapping(path="/companies")
    public CompanyDto createCompany(@RequestBody CompanyDto companyDto){
        Company company = companyMapper.mapFrom(companyDto);
        Company companySaved = companyService.createCompany(company);
        return companyMapper.mapTo(companySaved);
    }
}
