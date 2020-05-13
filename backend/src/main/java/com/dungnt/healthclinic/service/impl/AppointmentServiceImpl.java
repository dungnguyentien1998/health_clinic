package com.dungnt.healthclinic.service.impl;

import com.dungnt.healthclinic.model.Appointment;
import com.dungnt.healthclinic.repository.AppointmentRepository;
import com.dungnt.healthclinic.repository.CalendarRepository;
import com.dungnt.healthclinic.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AppointmentServiceImpl implements AppointmentService {
    private AppointmentRepository appointmentRepository;
    private CalendarRepository calendarRepository;

    @Autowired
    public AppointmentServiceImpl(AppointmentRepository appointmentRepository, CalendarRepository calendarRepository) {
        this.appointmentRepository = appointmentRepository;
        this.calendarRepository = calendarRepository;
    }

    @Override
    public List<Appointment> findAll() {
        return (List<Appointment>) appointmentRepository.findAll();
    }

    @Override
    public Optional<Appointment> findById(Integer id) {
        return appointmentRepository.findById(id);
    }

    @Override
    public void save(Appointment appointment) {
        appointmentRepository.save(appointment);
    }

    @Override
    public void remove(Appointment appointment) {
        appointmentRepository.delete(appointment);
    }
}
