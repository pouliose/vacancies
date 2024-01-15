package com.agency.vacancies.repositories;

import com.agency.vacancies.entites.Vacancy;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface VacancyRepository extends CrudRepository<Vacancy, Long> {
    Iterable<Vacancy> announcedDateTimeLessThan(LocalDateTime dateTime);

    @Query("SELECT v FROM Vacancy v WHERE v.announcedDateTime > ?1")
    Iterable<Vacancy> findVacanciesWithAnnouncedDateTimeMoreThan(LocalDateTime of);

    @Query("SELECT v FROM Vacancy v WHERE v.title LIKE %:keyword%")
    Iterable<Vacancy> findByTitleContainingHQL(@Param("keyword") String keyword);

    Iterable<Vacancy> findByTitleContaining(String keyword);
}
