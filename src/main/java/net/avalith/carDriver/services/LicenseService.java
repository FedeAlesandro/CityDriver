package net.avalith.carDriver.services;

import net.avalith.carDriver.models.License;
import net.avalith.carDriver.repositories.LicenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class LicenseService {

    @Autowired
    private LicenseRepository licenseRepository;

    public License save(License license){
        return licenseRepository.save(license);
    }

    public List<License> getAll(){
        return licenseRepository.findAll();
    }
}
