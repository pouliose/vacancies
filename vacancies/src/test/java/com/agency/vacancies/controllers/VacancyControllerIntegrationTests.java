package com.agency.vacancies.controllers;

import com.agency.vacancies.CreateTestDataUtil;
import com.agency.vacancies.domain.dto.VacancyDto;
import com.agency.vacancies.domain.entities.Vacancy;
import com.agency.vacancies.services.VacancyService;
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
public class VacancyControllerIntegrationTests {
    private MockMvc mockMvc;
    private ObjectMapper objectMapper;

    private VacancyService vacancyService;

    @Autowired
    public VacancyControllerIntegrationTests(MockMvc mockMvc, VacancyService vacancyService) {
        this.mockMvc = mockMvc;
        this.vacancyService = vacancyService;
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.findAndRegisterModules();
        this.objectMapper = objectMapper;
    }

    @Test
    public void testThatCreateVacancyReturnsHttpStatus201Created() throws Exception {
        VacancyDto testVacancyDtoA = CreateTestDataUtil.createTestVacancyDtoA(null);
        String createVacancyJson = objectMapper.writeValueAsString(testVacancyDtoA);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/vacancies")
                .contentType(MediaType.APPLICATION_JSON)
                .content(createVacancyJson)
        ).andExpect(MockMvcResultMatchers.status().isCreated()
        );
    }

    @Test
    public void testThatCreateVacancyReturnsCreateVacancy() throws Exception {
        VacancyDto testVacancyDtoA = CreateTestDataUtil.createTestVacancyDtoA(null);
        String createVacancyJson = objectMapper.writeValueAsString(testVacancyDtoA);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/vacancies")
                .contentType(MediaType.APPLICATION_JSON)
                .content(createVacancyJson)
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.id").value(testVacancyDtoA.getId())
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.title").value(testVacancyDtoA.getTitle())
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.announcedDateTime").value(testVacancyDtoA.getAnnouncedDateTime().toString())
        );
    }

    @Test
    public void testThatGetListVacanciesReturnsHttpStatus200Ok() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.get("/api/vacancies")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isOk()
        );
    }

    @Test
    public void testThatGetListVacanciesReturnsVacancies() throws Exception {
        Vacancy testVacancyA = CreateTestDataUtil.createTestVacancyA(null);
        vacancyService.createVacancy(testVacancyA);

        Vacancy testVacancyB = CreateTestDataUtil.createTestVacancyB(null);
        vacancyService.createVacancy(testVacancyB);

        mockMvc.perform(
                MockMvcRequestBuilders.get("/api/vacancies")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$[0].id").value(testVacancyA.getId())
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$[0].title").value(testVacancyA.getTitle())
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$[0].announcedDateTime").value(testVacancyA.getAnnouncedDateTime().toString())
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$[1].id").value(testVacancyB.getId())
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$[1].title").value(testVacancyB.getTitle())
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$[1].announcedDateTime").value(testVacancyB.getAnnouncedDateTime().toString())
        );
    }

    @Test
    public void testThatDeleteVacancyReturnsHttpStatus204ForNonExistingVacancy() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.delete("/api/vacancies/99")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isNoContent());
    }

    @Test
    public void testThatDeleteVacancyReturnsHttpStatus204ForAnExistingVacancy() throws Exception {
        Vacancy testVacancyA = CreateTestDataUtil.createTestVacancyA(null);
        vacancyService.createVacancy(testVacancyA);

        mockMvc.perform(
                MockMvcRequestBuilders.delete("/api/vacancies/" + testVacancyA.getId())
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isNoContent());
    }
}
