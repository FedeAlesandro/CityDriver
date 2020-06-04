package net.avalith.carDriver.services;

import net.avalith.carDriver.models.City;
import net.avalith.carDriver.repositories.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityService {

    @Autowired
    private CityRepository cityRepository;

    public City save(City city){
        return cityRepository.save(city);
    }

    public List<City> getAll(){
        return cityRepository.findAll();
    }
}
