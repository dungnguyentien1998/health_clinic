package com.dungnt.healthclinic.service.impl;

import com.dungnt.healthclinic.dto.CalendarRequest;
import com.dungnt.healthclinic.model.Calendar;
import com.dungnt.healthclinic.repository.CalendarRepository;
import com.dungnt.healthclinic.repository.ClinicServiceRepository;
import com.dungnt.healthclinic.service.CalendarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
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
    public Optional<Calendar> findById(Long id) throws Exception {
        if (id == null) {
            throw new Exception("Gia tri id null");
        }
        return calendarRepository.findById(id);
    }

    // Can kiem tra cac kieu Date, Time cho hop logic
    @Override
    public void save(Calendar calendar) throws Exception {
        if (calendar == null) {
            throw new Exception("Doi tuong calendar null");
        }
        if (calendar.getDate() == null) {
            throw new Exception("Gia tri date null");
        }
        if (calendar.getTimeStart() == null) {
            throw new Exception("Gia tri thoi gian bat dau null");
        }
        if (calendar.getTimeEnd() == null) {
            throw new Exception("Gia tri thoi gian ket thuc null");
        }
        if (calendar.getState() == null) {
            throw new Exception("Gia tri state null");
        }
        if (calendar.getRoom() == null) {
            throw new Exception("Gia tri phong kham null");
        }
        calendarRepository.save(calendar);
    }

    @Override
    public void remove(Calendar calendar) {
        calendarRepository.delete(calendar);
    }

    @Override
    public List<Calendar> findSuitableCalendar(CalendarRequest calendarRequest, Integer state) throws Exception {
        if (calendarRequest.getDate() == null) {
            throw new Exception("Gia tri date null");
        }
        if (calendarRequest.getTime() == null) {
            throw new Exception("Gia tri thoi gian bat dau null");
        }
        if (calendarRequest.getServiceId() == null) {
            throw new Exception("Gia tri serviceId null");
        }
        if (state == null) {
            throw new Exception("Gia tri state null");
        }
        LocalDate date = LocalDate.parse(calendarRequest.getDate());
        LocalTime timeStart = LocalTime.parse(calendarRequest.getTime());
        Long serviceId = Long.parseLong(calendarRequest.getServiceId());
        return calendarRepository.findSuitableCalendars(date, timeStart, serviceId, state);
    }

    @Override
    public List<Calendar> recommendCalendars(CalendarRequest calendarRequest, Integer state) throws Exception {
        if (calendarRequest.getDate() == null) {
            throw new Exception("Gia tri date null");
        }
        if (calendarRequest.getTime() == null) {
            throw new Exception("Gia tri thoi gian bat dau null");
        }
        if (calendarRequest.getServiceId() == null) {
            throw new Exception("Gia tri serviceId null");
        }
        if (state == null) {
            throw new Exception("Gia tri state null");
        }
        LocalDate date = LocalDate.parse(calendarRequest.getDate());
        LocalTime timeStart = LocalTime.parse(calendarRequest.getTime());
        Long serviceId = Long.parseLong(calendarRequest.getServiceId());
        return calendarRepository.findRecommendedCalendars(date, timeStart, serviceId, state);
    }


}
