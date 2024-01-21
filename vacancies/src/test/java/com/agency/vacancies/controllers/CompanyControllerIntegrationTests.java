package com.agency.vacancies.controllers;

import com.agency.vacancies.CreateTestDataUtil;
import com.agency.vacancies.domain.entities.Company;
import com.agency.vacancies.services.CompanyService;
import com.fasterxml.jackson.core.JsonProcessingException;
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
    public void testThatCreateCompanySuccesfullyReturnsHttp201Create() throws Exception {
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
    public void testThatCreateCompanySuccesfullyReturnsSavedCompany() throws Exception {
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
        Company companyA = CreateTestDataUtil.createTestCompanyA();
        companyService.createCompany(companyA);

        mockMvc.perform(
                        MockMvcRequestBuilders.get("/api/companies")
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(
                        MockMvcResultMatchers.jsonPath("$[0].id").isNumber()
                ).andExpect(
                        MockMvcResultMatchers.jsonPath("$[0].name").value(companyA.getName())
                );
    }

}
