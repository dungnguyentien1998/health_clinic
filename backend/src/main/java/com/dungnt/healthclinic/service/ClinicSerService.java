package com.dungnt.healthclinic.service;

import com.dungnt.healthclinic.model.ClinicService;

import java.util.List;
import java.util.Optional;

public interface ClinicSerService {
    List<ClinicService> findAll();

    Optional<ClinicService> findById(Integer id);

    void save(ClinicService clinicService);

    void remove(ClinicService clinicService);
}
