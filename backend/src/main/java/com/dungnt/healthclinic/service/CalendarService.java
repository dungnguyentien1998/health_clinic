package com.dungnt.healthclinic.service;

import com.dungnt.healthclinic.model.Calendar;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface CalendarService {
    List<Calendar> findAll();

    Optional<Calendar> findById(Integer id);

    void save(Calendar calendar);

    void remove(Calendar calendar);

    Page<Calendar> findAllCalendarsByClinicServiceId(Integer clinicServiceId, Pageable pageable);

    Optional<Calendar> findCalendarByIdAndClinicServiceId(Integer calendarId, Integer clinicServiceId);
}
