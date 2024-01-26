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
        VacancyDto vacancyDto = CreateTestDataUtil.createTestVacancyDtoA(null);
        String createVacancyJson = objectMapper.writeValueAsString(vacancyDto);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/vacancies")
                .contentType(MediaType.APPLICATION_JSON)
                .content(createVacancyJson)
        ).andExpect(MockMvcResultMatchers.status().isCreated()
        );
    }

    @Test
    public void testThatCreateVacancyReturnsCreateVacancy() throws Exception {
        VacancyDto vacancyDto = CreateTestDataUtil.createTestVacancyDtoA(null);
        String createVacancyJson = objectMapper.writeValueAsString(vacancyDto);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/vacancies")
                .contentType(MediaType.APPLICATION_JSON)
                .content(createVacancyJson)
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.id").value(vacancyDto.getId())
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.title").value(vacancyDto.getTitle())
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.announcedDateTime").value(vacancyDto.getAnnouncedDateTime().toString())
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
        Vacancy vacancy1 = CreateTestDataUtil.createTestVacancyA(null);
        vacancyService.createVacancy(vacancy1);

        Vacancy vacancy2 = CreateTestDataUtil.createTestVacancyB(null);
        vacancyService.createVacancy(vacancy2);

        mockMvc.perform(
                MockMvcRequestBuilders.get("/api/vacancies")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$[0].id").value(vacancy1.getId())
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$[0].title").value(vacancy1.getTitle())
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$[0].announcedDateTime").value(vacancy1.getAnnouncedDateTime().toString())
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$[1].id").value(vacancy2.getId())
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$[1].title").value(vacancy2.getTitle())
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$[1].announcedDateTime").value(vacancy2.getAnnouncedDateTime().toString())
        );
    }
}
