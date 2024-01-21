package com.agency.vacancies.controllers;

import com.agency.vacancies.domain.dto.CompanyDto;
import com.agency.vacancies.domain.entities.Company;
import com.agency.vacancies.mappers.Mapper;
import com.agency.vacancies.services.CompanyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path="/api")
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
    public ResponseEntity<CompanyDto> createCompany(@RequestBody CompanyDto companyDto){
        Company company = companyMapper.mapFrom(companyDto);
        Company companySaved = companyService.createCompany(company);
        CompanyDto companyDtoSaved = companyMapper.mapTo(companySaved);
        return new ResponseEntity(companyDtoSaved, HttpStatus.CREATED);
    }

    @GetMapping(path="/companies")
    public List<CompanyDto> listCompanies(){
        List<Company> companies = companyService.findAll();
        return companies
                .stream()
                .map(companyMapper::mapTo)
                .collect(Collectors.toList());
    }
}
