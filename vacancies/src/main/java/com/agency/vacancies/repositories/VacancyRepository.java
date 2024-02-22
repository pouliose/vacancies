package com.agency.vacancies.repositories;

import com.agency.vacancies.domain.entities.Vacancy;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;

public interface VacancyRepository extends CrudRepository<Vacancy, Long>, PagingAndSortingRepository<Vacancy, Long> {
    Iterable<Vacancy> announcedDateTimeLessThan(LocalDateTime dateTime);

    @Query("SELECT v FROM Vacancy v WHERE v.announcedDateTime > ?1")
    Iterable<Vacancy> findVacanciesWithAnnouncedDateTimeMoreThan(LocalDateTime of);

    @Query("SELECT v FROM Vacancy v WHERE v.title LIKE %:keyword%")
    Iterable<Vacancy> findByTitleContainingHQL(@Param("keyword") String keyword);

    Iterable<Vacancy> findByTitleContaining(String keyword);
}
