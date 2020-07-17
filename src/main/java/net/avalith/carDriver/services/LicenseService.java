package net.avalith.carDriver.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import net.avalith.carDriver.exceptions.AlreadyExistsException;
import net.avalith.carDriver.exceptions.NotFoundException;
import net.avalith.carDriver.models.License;
import net.avalith.carDriver.models.dtos.requests.LicenseDtoRequest;
import net.avalith.carDriver.repositories.LicenseRepository;
import net.avalith.carDriver.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static net.avalith.carDriver.utils.Constants.*;

@Repository
@AllArgsConstructor
public class LicenseService {

    @Autowired
    private final LicenseRepository licenseRepository;

    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private final RedisTemplate<String, License> redisTemplate;

    public License save(LicenseDtoRequest license){

        if(licenseRepository.findByNumber(license.getNumber()).isPresent())
                throw new AlreadyExistsException(LICENSE_ALREADY_EXISTS);

        userRepository.getByDni(license.getNumber())
                .orElseThrow(() -> new NotFoundException(NOT_FOUND_LICENSE_USER));

        License newLicense = new License(license);

        LocalDateTime expirationDate = new Timestamp(license.getExpirationDate().getTime())
                .toLocalDateTime();

        newLicense.setValidated(expirationDate.isBefore(LocalDateTime.now().minusDays(15L)));

        newLicense = licenseRepository.save(newLicense);
        redisTemplate.opsForHash().put(LICENSE_KEY, newLicense.getId(), newLicense);

        return newLicense;
    }

    public List<License> getAll(){
        ObjectMapper objectMapper = new ObjectMapper();
        List<License> list = new ArrayList<>();
        String json = "";

        if(redisTemplate.opsForHash().keys(LICENSE_KEY).isEmpty()){
            licenseRepository.findAll()
                    .forEach((License license) -> redisTemplate.opsForHash().put(LICENSE_KEY, license.getId(), license));
            redisTemplate.boundHashOps(LICENSE_KEY).expire(24L, TimeUnit.HOURS);
        }

        try {
            json = objectMapper.writeValueAsString(redisTemplate.opsForHash().values(LICENSE_KEY));
            list = objectMapper.readValue(json, new TypeReference<List<License>>(){});
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return list;
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
