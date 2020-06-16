package net.avalith.carDriver.services;

import net.avalith.carDriver.exceptions.AlreadyExistsException;
import net.avalith.carDriver.exceptions.NotFoundException;
import net.avalith.carDriver.models.Country;
import net.avalith.carDriver.repositories.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static net.avalith.carDriver.utils.Constants.COUNTRY_ALREADY_EXISTS;
import static net.avalith.carDriver.utils.Constants.NOT_FOUND_COUNTRY;

@Service
public class CountryService {

    @Autowired
    private CountryRepository countryRepository;

    public Country save(Country country){

        if(countryRepository.findByName(country.getName()).isPresent())
            throw new AlreadyExistsException(COUNTRY_ALREADY_EXISTS);

        return countryRepository.save(country);
    }

    public List<Country> getAll(){

        return countryRepository.findAll();
    }

    public Country update(String name, Country country) {

        name = name.replace("-", " ");

        Country oldCountry = countryRepository.findByName(name)
                .orElseThrow(() -> new NotFoundException(NOT_FOUND_COUNTRY));

        if(!country.getName().equals(oldCountry.getName()))
            if(countryRepository.findByName(country.getName()).isPresent())
                throw new AlreadyExistsException(COUNTRY_ALREADY_EXISTS);

        country.setId(oldCountry.getId());

        return countryRepository.save(country);
    }
}
