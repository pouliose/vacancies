package com.agency.vacancies.controllers;

import com.agency.vacancies.CreateTestDataUtil;
import com.agency.vacancies.domain.dto.CompanyDto;
import com.agency.vacancies.domain.entities.Company;
import com.agency.vacancies.services.CompanyService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@AutoConfigureMockMvc
public class CompanyControllerIntegrationTests {
    private MockMvc mockMvc;

    private CompanyService companyService;
    private ObjectMapper objectMapper;

    @Autowired
    public CompanyControllerIntegrationTests(MockMvc mockMvc, CompanyService companyService, ObjectMapper objectMapper) {
        this.mockMvc = mockMvc;
        this.companyService = companyService;
        this.objectMapper = objectMapper;
    }

    @Test
    public void testThatCreateCompanySuccessfullyReturnsHttp201Create() throws Exception {
        Company testCompanyA = CreateTestDataUtil.createTestCompanyA();
        testCompanyA.setId(null);
        String testCompanyAJson = objectMapper.writeValueAsString(testCompanyA);

        mockMvc.perform(
                MockMvcRequestBuilders
                        .post("/api/companies")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(testCompanyAJson)
        ).andExpect(
                MockMvcResultMatchers.status().isCreated()
        );
    }

    @Test
    public void testThatCreateCompanySuccessfullyReturnsSavedCompany() throws Exception {
        Company testCompanyA = CreateTestDataUtil.createTestCompanyA();
        testCompanyA.setId(null);
        String testCompanyAJson = objectMapper.writeValueAsString(testCompanyA);

        mockMvc.perform(
                MockMvcRequestBuilders
                        .post("/api/companies")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(testCompanyAJson)
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.id").isNumber()
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.name").value(testCompanyA.getName())
        );
    }

    @Test
    public void testThatListCompaniesReturnsHttpStatus200() throws Exception {
        mockMvc.perform(
                        MockMvcRequestBuilders.get("/api/companies")
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testThatListCompaniesReturnsListOfCompanies() throws Exception {
        Company testCompanyA = CreateTestDataUtil.createTestCompanyA();
        companyService.saveCompany(testCompanyA);

        mockMvc.perform(
                        MockMvcRequestBuilders.get("/api/companies")
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(
                        MockMvcResultMatchers.jsonPath("$[0].id").isNumber()
                ).andExpect(
                        MockMvcResultMatchers.jsonPath("$[0].name").value(testCompanyA.getName())
                );
    }

    @Test
    public void testThatGetCompanyReturnsHttpStatus200WhenCompanyExists() throws Exception {
        Company testCompanyA = CreateTestDataUtil.createTestCompanyA();
        companyService.saveCompany(testCompanyA);

        mockMvc.perform(
                        MockMvcRequestBuilders.get("/api/companies/1")
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testThatGetCompanyReturnsCompanyWhenCompanyExists() throws Exception {
        Company testCompanyA = CreateTestDataUtil.createTestCompanyA();
        companyService.saveCompany(testCompanyA);

        mockMvc.perform(
                        MockMvcRequestBuilders.get("/api/companies/1")
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(
                        MockMvcResultMatchers.jsonPath("$.id").value(1)
                ).andExpect(
                        MockMvcResultMatchers.jsonPath("$.name").value(testCompanyA.getName())
                );
    }

    @Test
    public void testThatGetCompanyReturnsHttpStatus404WhenAnyCompanyExists() throws Exception {
        mockMvc.perform(
                        MockMvcRequestBuilders.get("/api/companies/1")
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void testThatFullUpdateCompanyReturnsHttpStatus404WhenAnyCompanyExists() throws Exception {
        CompanyDto testCompanyDtoA = CreateTestDataUtil.createTestCompanyDtoA();
        String companyDtoJson = objectMapper.writeValueAsString(testCompanyDtoA);

        mockMvc.perform(
                        MockMvcRequestBuilders.put("/api/companies/99")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(companyDtoJson))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void testThatFullUpdateCompanyReturnsHttpStatus200WhenCompanyExists() throws Exception {
        Company testCompanyA = CreateTestDataUtil.createTestCompanyA();
        Company savedCompany = companyService.saveCompany(testCompanyA);

        CompanyDto testCompanyDtoA = CreateTestDataUtil.createTestCompanyDtoA();
        String companyDtoJson = objectMapper.writeValueAsString(testCompanyDtoA);

        mockMvc.perform(
                        MockMvcRequestBuilders.put("/api/companies/" + savedCompany.getId())
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(companyDtoJson))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testThatFullUpdateUpdatesExistingCompany() throws Exception {
        Company testCompanyA = CreateTestDataUtil.createTestCompanyA();
        Company savedCompanyA = companyService.saveCompany(testCompanyA);

        CompanyDto testCompanyDtoB = CreateTestDataUtil.createTestCompanyDtoB();
        testCompanyDtoB.setId(savedCompanyA.getId());

        String companyDtoUpdatJson = objectMapper.writeValueAsString(testCompanyDtoB);

        mockMvc.perform(
                        MockMvcRequestBuilders.put("/api/companies/" + savedCompanyA.getId())
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(companyDtoUpdatJson))
                .andExpect(
                        MockMvcResultMatchers.jsonPath("$.id").value(savedCompanyA.getId())
                ).andExpect(
                        MockMvcResultMatchers.jsonPath("$.name").value(testCompanyDtoB.getName())
                );
    }

    @Test
    public void testThatDeleteCompanyReturnsHttpStatus204ForNonExistingCompany() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.delete("/api/companies/99")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isNoContent());
    }

    @Test
    public void testThatDeleteCompanyReturnsHttpStatus204ForAnExistingCompany() throws Exception {
        Company testCompanyA = CreateTestDataUtil.createTestCompanyA();
        Company savedCompanyA = companyService.saveCompany(testCompanyA);

        mockMvc.perform(
                MockMvcRequestBuilders.delete("/api/companies/" + testCompanyA.getId())
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isNoContent());
    }

}
