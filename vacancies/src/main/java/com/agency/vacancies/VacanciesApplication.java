package com.agency.vacancies;

import lombok.extern.java.Log;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Log
public class VacanciesApplication {

	public static void main(String[] args) {
		SpringApplication.run(VacanciesApplication.class, args);
	}

}
