package net.avalith.carDriver.services;

import net.avalith.carDriver.exceptions.AlreadyExistsException;
import net.avalith.carDriver.exceptions.NotFoundException;
import net.avalith.carDriver.models.City;
import net.avalith.carDriver.models.Country;
import net.avalith.carDriver.models.dtos.CityDto;
import net.avalith.carDriver.repositories.CityRepository;
import net.avalith.carDriver.repositories.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static net.avalith.carDriver.utils.Constants.CITY_ALREADY_EXISTS;
import static net.avalith.carDriver.utils.Constants.NOT_FOUND_CITY;
import static net.avalith.carDriver.utils.Constants.NOT_FOUND_COUNTRY;

@Service
public class CityService {

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private CountryRepository countryRepository;

    public City save(CityDto city){
        Country country = countryRepository.findByName(city.getCountryName())
            .orElseThrow(() -> new NotFoundException(NOT_FOUND_COUNTRY));

        if(cityRepository.findByName(city.getName()).isPresent())
            throw new AlreadyExistsException(CITY_ALREADY_EXISTS);
        
        return cityRepository.save(new City(city, country));
    }

    public List<City> getAll(){

        return cityRepository.findAll();
    }

    public City update(String name, CityDto city) {
        name = name.replace("-", " ");

        City oldCity = cityRepository.findByName(name)
                .orElseThrow(() -> new NotFoundException(NOT_FOUND_CITY));

        if(!city.getName().equals(oldCity.getName()))
            if(cityRepository.findByName(city.getName()).isPresent())
                throw new AlreadyExistsException(CITY_ALREADY_EXISTS);

        Country country = countryRepository.findByName(city.getCountryName())
                .orElseThrow(() -> new NotFoundException(NOT_FOUND_COUNTRY));

        City updatedCity = new City(city, country);
        updatedCity.setId(oldCity.getId());

        return cityRepository.save(updatedCity);
    }
}
