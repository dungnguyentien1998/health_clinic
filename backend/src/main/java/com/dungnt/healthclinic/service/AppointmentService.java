package com.dungnt.healthclinic.service;

import com.dungnt.healthclinic.model.Appointment;

import java.util.List;
import java.util.Optional;

public interface AppointmentService {
    List<Appointment> findAll();

    Optional<Appointment> findById(Integer id);

    void save(Appointment appointment);

    void remove(Appointment appointment);

}
