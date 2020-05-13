package com.dungnt.healthclinic.service.impl;

import com.dungnt.healthclinic.model.ClinicService;
import com.dungnt.healthclinic.repository.ClinicServiceRepository;
import com.dungnt.healthclinic.service.ClinicSerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClinicSerServiceImpl implements ClinicSerService {
    private ClinicServiceRepository clinicServiceRepository;

    @Autowired
    public ClinicSerServiceImpl(ClinicServiceRepository clinicServiceRepository) {
        this.clinicServiceRepository = clinicServiceRepository;
    }


    @Override
    public List<ClinicService> findAll() {
        return (List<ClinicService>) clinicServiceRepository.findAll();
    }

    @Override
    public Optional<ClinicService> findById(Integer id) {
        return clinicServiceRepository.findById(id);
    }

    @Override
    public void save(ClinicService clinicService) {
        clinicServiceRepository.save(clinicService);
    }

    @Override
    public void remove(ClinicService clinicService) {
        clinicServiceRepository.delete(clinicService);
    }
}
