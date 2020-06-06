package net.avalith.carDriver.services;

import net.avalith.carDriver.models.City;
import net.avalith.carDriver.models.Country;
import net.avalith.carDriver.models.dtos.CityDto;
import net.avalith.carDriver.repositories.CityRepository;
import net.avalith.carDriver.repositories.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityService {

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private CountryRepository countryRepository;

    public City save(CityDto city){
        Country country = countryRepository.findByName(city.getCountryName())
            .orElseThrow(RuntimeException::new); //todo excepcion custom
        return cityRepository.save(City.fromCityDto(city, country));
    }

    public List<City> getAll(){
        return cityRepository.findAll();
    }
}
