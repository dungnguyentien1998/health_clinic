package com.dungnt.healthclinic.controller;

import com.dungnt.healthclinic.model.Appointment;
import com.dungnt.healthclinic.model.Calendar;
import com.dungnt.healthclinic.service.AppointmentService;
import com.dungnt.healthclinic.service.CalendarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class AppointmentController {
    private AppointmentService appointmentService;
    private CalendarService calendarService;

    @Autowired
    public AppointmentController(AppointmentService appointmentService, CalendarService calendarService) {
        this.appointmentService = appointmentService;
        this.calendarService = calendarService;
    }

    @RequestMapping(value = "/appointments", method = RequestMethod.GET)
    public ResponseEntity<List<Appointment>> findAllAppointments() {
        List<Appointment> appointments = appointmentService.findAll();
        if (appointments.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(appointments, HttpStatus.OK);
    }

    @RequestMapping(value = "/appointments/{id}", method = RequestMethod.GET)
    public ResponseEntity<Appointment> findAppointmentById(@PathVariable("id") Integer id) {
        Optional<Appointment> appointment = appointmentService.findById(id);
        if (!appointment.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(appointment.get(), HttpStatus.OK);
    }

    @RequestMapping(value = "/calendars/{calendarId}/clients/{clientId}/medicalStaffs/{staffId}/appointments",
            method = RequestMethod.POST)
    public ResponseEntity<Appointment> createAppointment(@PathVariable("calendarId") Integer calendarId,
                                                         @PathVariable("clientId") Integer clientId,
                                                         @PathVariable("staffId") Integer medicalStaffId,
                                                         @RequestBody Appointment appointment) {
        Optional<Calendar> calendar = calendarService.findById(calendarId);
        if (!calendar.isPresent()) {
            // Can tao message cho truong hop nay
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        appointment.setCalendar(calendar.get());
        appointmentService.save(appointment);
        return new ResponseEntity<>(appointment, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/appointments/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Appointment> deleteAppointment(@PathVariable("id") Integer id) {
        Optional<Appointment> appointment = appointmentService.findById(id);
        if (!appointment.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        appointmentService.remove(appointment.get());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
