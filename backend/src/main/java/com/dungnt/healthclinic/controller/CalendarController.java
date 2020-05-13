package com.dungnt.healthclinic.controller;

import com.dungnt.healthclinic.model.Calendar;
import com.dungnt.healthclinic.model.ClinicService;
import com.dungnt.healthclinic.service.CalendarService;
import com.dungnt.healthclinic.service.ClinicSerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class CalendarController {
    private ClinicSerService clinicSerService;
    private CalendarService calendarService;

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
    public ResponseEntity<Calendar> findCalendarById(@PathVariable("id") Integer id) {
        Optional<Calendar> calendar = calendarService.findById(id);
        if (!calendar.isPresent()) {
            return new ResponseEntity<>(calendar.get(), HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(calendar.get(), HttpStatus.OK);
    }

    @RequestMapping(value = "/clinicservices/{clinicserviceId}/calendars", method = RequestMethod.POST)
    public ResponseEntity<Calendar> createCalendar(@PathVariable("clinicserviceId") Integer clinicServiceId,
                                                   @RequestBody Calendar calendar) {
        Optional<ClinicService> clinicService = clinicSerService.findById(clinicServiceId);
        if (!clinicService.isPresent()) {
            // Can tao message cho truong hop nay
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        calendar.setClinicService(clinicService.get());
        calendarService.save(calendar);
        return new ResponseEntity<>(calendar, HttpStatus.CREATED);
    }

    //    @RequestMapping(value = "/clinicservices/{clinicserviceId}/calendars/{calendarId}")
//    public ResponseEntity<Calendar> updateCalendar(@PathVariable("clinicserviceId") Integer clinicServiceId,
//                                                    @PathVariable("calendarId") Integer calendarId,
//                                                    @RequestBody Calendar calendar) {
//
//    }
    @RequestMapping(value = "/calendars/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Calendar> updateCalendar(@PathVariable("id") Integer id, @RequestBody Calendar calendar) {
        Optional<Calendar> currentCalendar = calendarService.findById(id);
        if (!currentCalendar.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        currentCalendar.get().setDate(calendar.getDate());
        currentCalendar.get().setTime(calendar.getTime());
        currentCalendar.get().setState(calendar.getState());
        currentCalendar.get().setRoom(calendar.getRoom());
        currentCalendar.get().setClinicService(calendar.getClinicService());

        calendarService.save(currentCalendar.get());
        return new ResponseEntity<>(currentCalendar.get(), HttpStatus.OK);
    }

    @RequestMapping(value = "/calendars/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Calendar> deleteCalendar(@PathVariable("id") Integer id) {
        Optional<Calendar> calendar = calendarService.findById(id);
        if (!calendar.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        calendarService.remove(calendar.get());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
