package com.dungnt.healthclinic.repository;

import com.dungnt.healthclinic.model.Calendar;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CalendarRepository extends JpaRepository<Calendar, Integer> {
    Page<Calendar> findByClinicServiceId(Integer clinicServiceId, Pageable pageable);
    Optional<Calendar> findByIdAndClinicServiceId(Integer id, Integer clinicServiceId);

    @Query(value = "Select ca.* from calendars ca, clinic_services cl where ca.service_id = cl.id", nativeQuery = true)
    Calendar findAllWithClinicServiceId();
}
