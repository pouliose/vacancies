package com.agency.vacancies.controllers;

import com.agency.vacancies.domain.dto.CompanyDto;
import com.agency.vacancies.domain.entities.Company;
import com.agency.vacancies.mappers.Mapper;
import com.agency.vacancies.repositories.CompanyRepository;
import com.agency.vacancies.services.CompanyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/api")
public class CompanyController {
    private CompanyService companyService;
    private Mapper<Company, CompanyDto> companyMapper;
    private final CompanyRepository companyRepository;

    public CompanyController(CompanyService companyService, Mapper<Company, CompanyDto> companyMapper,
                             CompanyRepository companyRepository) {
        this.companyService = companyService;
        this.companyMapper = companyMapper;
        this.companyRepository = companyRepository;
    }

    @GetMapping(path = "/")
    public String welcome() {
        return "Welcome";
    }

    @PostMapping(path = "/companies")
    public ResponseEntity<CompanyDto> createCompany(@RequestBody CompanyDto companyDto) {
        Company company = companyMapper.mapFrom(companyDto);
        Company companySaved = companyService.saveCompany(company);
        CompanyDto companyDtoSaved = companyMapper.mapTo(companySaved);
        return new ResponseEntity<>(companyDtoSaved, HttpStatus.CREATED);
    }

    @GetMapping(path = "/companies")
    public List<CompanyDto> listCompanies() {
        List<Company> companies = companyService.findAll();
        return companies
                .stream()
                .map(companyMapper::mapTo)
                .collect(Collectors.toList());
    }

    @GetMapping(path = "/companies/{id}")
    public ResponseEntity<CompanyDto> getCompany(@PathVariable("id") Long id) {
        Optional<Company> companyFound = companyService.findOne(id);

        return companyFound.map(company -> {
                    CompanyDto companyDto = companyMapper.mapTo(company);
                    return new ResponseEntity<>(companyDto, HttpStatus.OK);
                })
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping(path = "/companies/{id}")
    public ResponseEntity<CompanyDto> fullUpdateCompany(@PathVariable("id") Long id, @RequestBody CompanyDto companyDto) {
        if (!companyService.isExists(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        companyDto.setId(id);
        Company company = companyMapper.mapFrom(companyDto);
        Company savedCompany = companyService.saveCompany(company);

        return new ResponseEntity<>(
                companyMapper.mapTo(savedCompany),
                HttpStatus.OK);
    }

    @DeleteMapping(path = "/companies/{id}")
    public ResponseEntity deleteCompany(@PathVariable("id") Long id) {
        companyService.delete(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

}
