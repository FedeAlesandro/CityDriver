package net.avalith.carDriver.services;

import net.avalith.carDriver.models.Country;
import net.avalith.carDriver.repositories.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountryService {

    @Autowired
    private CountryRepository countryRepository;

    public Country save(Country country){
        return countryRepository.save(country);
    }

    public List<Country> getAll(){
        return countryRepository.findAll();
    }
}
