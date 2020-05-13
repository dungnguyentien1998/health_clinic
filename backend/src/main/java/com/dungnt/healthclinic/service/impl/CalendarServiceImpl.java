package com.dungnt.healthclinic.service.impl;

import com.dungnt.healthclinic.model.Calendar;
import com.dungnt.healthclinic.repository.CalendarRepository;
import com.dungnt.healthclinic.repository.ClinicServiceRepository;
import com.dungnt.healthclinic.service.CalendarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CalendarServiceImpl implements CalendarService {
    private CalendarRepository calendarRepository;
    private ClinicServiceRepository clinicServiceRepository;

    @Autowired
    public CalendarServiceImpl(CalendarRepository calendarRepository, ClinicServiceRepository clinicServiceRepository) {
        this.calendarRepository = calendarRepository;
        this.clinicServiceRepository  = clinicServiceRepository;
    }

    @Override
    public List<Calendar> findAll() {
        return (List<Calendar>) calendarRepository.findAll();
    }

    @Override
    public Optional<Calendar> findById(Integer id) {
        return calendarRepository.findById(id);
    }

    @Override
    public void save(Calendar calendar) {
        calendarRepository.save(calendar);
    }

    @Override
    public void remove(Calendar calendar) {
        calendarRepository.delete(calendar);
    }

    @Override
    public Page<Calendar> findAllCalendarsByClinicServiceId(Integer clinicServiceId, Pageable pageable) {
        return calendarRepository.findByClinicServiceId(clinicServiceId, pageable);
    }

//    @Override
//    public Optional<Calendar> findCalendarByIdAndClinicServiceId(Integer calendarId, Integer clinicServiceId) {
//        return calendarRepository.findByIdAndClinicServiceId(calendarId, clinicServiceId);
//    }
}
