package com.dungnt.healthclinic.repository;

import com.dungnt.healthclinic.model.Calendar;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface CalendarRepository extends JpaRepository<Calendar, Long> {
    Page<Calendar> findByClinicServiceId(Long clinicServiceId, Pageable pageable);

    @Query(value = "Select ca.* from calendars ca where ca.date = :date and ca.time_start = :timeStart and ca.service_id = :id and ca.state = :state", nativeQuery = true)
    List<Calendar> findSuitableCalendars(@Param("date")LocalDate date, @Param("timeStart")LocalTime timeStart, @Param("id")Long serviceId, @Param("state") Integer state);

    @Query(value = "Select ca.* from calendars ca where ca.date >= :date and ca.time_start = :timeStart and ca.service_id = :id and ca.state = :state", nativeQuery = true)
    List<Calendar> findRecommendedCalendars(@Param("date")LocalDate date, @Param("timeStart")LocalTime timeStart, @Param("id")Long serviceId, @Param("state") Integer state);
}
