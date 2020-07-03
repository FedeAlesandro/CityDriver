package net.avalith.carDriver.services;

import lombok.AllArgsConstructor;
import net.avalith.carDriver.exceptions.AlreadyExistsException;
import net.avalith.carDriver.exceptions.NotFoundException;
import net.avalith.carDriver.models.License;
import net.avalith.carDriver.models.dtos.requests.LicenseDtoRequest;
import net.avalith.carDriver.repositories.LicenseRepository;
import net.avalith.carDriver.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

import static net.avalith.carDriver.utils.Constants.LICENSE_ALREADY_EXISTS;
import static net.avalith.carDriver.utils.Constants.NOT_FOUND_LICENSE;
import static net.avalith.carDriver.utils.Constants.NOT_FOUND_LICENSE_USER;

@Repository
@AllArgsConstructor
public class LicenseService {

    @Autowired
    private final LicenseRepository licenseRepository;

    @Autowired
    private final UserRepository userRepository;

    public License save(LicenseDtoRequest license){

        if(licenseRepository.findByNumber(license.getNumber()).isPresent())
                throw new AlreadyExistsException(LICENSE_ALREADY_EXISTS);

        userRepository.getByDni(license.getNumber())
                .orElseThrow(() -> new NotFoundException(NOT_FOUND_LICENSE_USER));

        return licenseRepository.save(new License(license));
    }

    public List<License> getAll(){

        return licenseRepository.findAll();
    }
/*
    public License update(String number, LicenseDtoRequest license) {
        License oldLicense = licenseRepository.findByNumber(number)
                .orElseThrow(() -> new NotFoundException(NOT_FOUND_LICENSE));

        if(!license.getNumber().equals(oldLicense.getNumber()))
            if(licenseRepository.findByNumber(license.getNumber()).isPresent())
                throw new AlreadyExistsException(LICENSE_ALREADY_EXISTS);

        userRepository.getByDni(license.getNumber())
                .orElseThrow(() -> new NotFoundException(NOT_FOUND_LICENSE_USER));

        License licenseUpdate = new License(license);
        licenseUpdate.setId(oldLicense.getId());
        licenseUpdate.setExpirationDate(oldLicense.getExpirationDate());
        licenseUpdate.setValidated(oldLicense.getValidated());

        return licenseRepository.save(licenseUpdate);
    }*/
}
