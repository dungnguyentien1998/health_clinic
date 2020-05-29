package com.dungnt.healthclinic.controller;

import com.dungnt.healthclinic.dto.CalendarRequest;
import com.dungnt.healthclinic.model.Calendar;
import com.dungnt.healthclinic.model.ClinicService;
import com.dungnt.healthclinic.service.CalendarService;
import com.dungnt.healthclinic.service.ClinicSerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

@RestController
public class CalendarController {
    private ClinicSerService clinicSerService;
    private CalendarService calendarService;
    private Optional<ClinicService> newClinicService;

    @Autowired
    public CalendarController(ClinicSerService clinicSerService, CalendarService calendarService) {
        this.clinicSerService = clinicSerService;
        this.calendarService = calendarService;
    }

    @RequestMapping(value = "/calendars", method = RequestMethod.GET)
    public ResponseEntity<List<Calendar>> findAllCalendars() {
        List<Calendar> calendars = calendarService.findAll();
        if (calendars.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(calendars, HttpStatus.OK);
    }

    @RequestMapping(value = "/calendars/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Calendar> findCalendarById(@PathVariable("id") Long id) throws Exception {
        Optional<Calendar> calendar = calendarService.findById(id);
        if (!calendar.isPresent()) {
            return new ResponseEntity<>(calendar.get(), HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(calendar.get(), HttpStatus.OK);
    }

    ///////////////////
    @RequestMapping(value = "/clinicservices/{clinicserviceId}/calendars", method = RequestMethod.POST)
    public ResponseEntity<Calendar> createCalendar(@PathVariable("clinicserviceId") Long clinicServiceId,
                                                   @RequestBody Calendar calendar) throws Exception {
        Optional<ClinicService> clinicService = clinicSerService.findById(clinicServiceId);
        if (!clinicService.isPresent()) {
            // Can tao message cho truong hop nay
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        calendar.setClinicService(clinicService.get());
        calendarService.save(calendar);
        return new ResponseEntity<>(calendar, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/calendars/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Calendar> updateCalendar(@PathVariable("id") Long id, @RequestBody Calendar calendar,
                                                   @RequestParam("clinicServiceId") Long clinicServiceId) throws Exception {
        Optional<Calendar> currentCalendar = calendarService.findById(id);
        if (!currentCalendar.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        currentCalendar.get().setDate(calendar.getDate());
        currentCalendar.get().setTimeStart(calendar.getTimeStart());
        currentCalendar.get().setTimeEnd(calendar.getTimeEnd());
        currentCalendar.get().setState(calendar.getState());
        currentCalendar.get().setRoom(calendar.getRoom());

        if (clinicServiceId != null) {
            Optional<ClinicService> newClinicService = clinicSerService.findById(clinicServiceId);
            if (!newClinicService.isPresent()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            currentCalendar.get().setClinicService(newClinicService.get());
        }

        calendarService.save(currentCalendar.get());
        return new ResponseEntity<>(currentCalendar.get(), HttpStatus.OK);
    }

    @RequestMapping(value = "/calendars/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Calendar> deleteCalendar(@PathVariable("id") Long id) throws Exception {
        Optional<Calendar> calendar = calendarService.findById(id);
        if (!calendar.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        calendarService.remove(calendar.get());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "/getCalendars", method = RequestMethod.POST)
    public ResponseEntity<List<Calendar>> getSuitableCalendars(@RequestBody CalendarRequest calendarRequest) throws Exception {
//        DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//        LocalDate date = LocalDate.parse(calendarRequest.getDate(), formatter1);
//        DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("HH:mm:ss");
//        LocalTime timeStart = LocalTime.parse(calendarRequest.getTime(), formatter2);
//        LocalDate date = LocalDate.parse(calendarRequest.getDate());
//        LocalTime timeStart = LocalTime.parse(calendarRequest.getTime());
//        Long serviceId = Long.parseLong(calendarRequest.getServiceId());
        List<Calendar> suitableCalendars = calendarService.findSuitableCalendar(calendarRequest, 0);
        if (suitableCalendars.isEmpty()) {
            List<Calendar> recommendedCalendars = calendarService.recommendCalendars(calendarRequest, 0);
            return new ResponseEntity<>(recommendedCalendars, HttpStatus.OK);
        } else {
            // cap nhat trang thai state o day
            Long calendarId = suitableCalendars.get(0).getId();
            Optional<Calendar> calendar = calendarService.findById(calendarId);
            if (calendar.isPresent() && calendar.get().getState() == 0){
                calendar.get().setState(1);
                calendarService.save(calendar.get());
            }
            return new ResponseEntity<>(suitableCalendars, HttpStatus.OK);
        }
    }
}
