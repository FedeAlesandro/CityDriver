package net.avalith.carDriver.controllers;

import net.avalith.carDriver.models.City;
import net.avalith.carDriver.services.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/cities")
public class CityController {

    @Autowired
    private CityService cityService;

    @PostMapping("/")
    public ResponseEntity<City> save(City city){
        return ResponseEntity.ok(cityService.save(city));
    }

    @GetMapping("/")
    public ResponseEntity<List<City>> getAll(){
        List<City> cities = cityService.getAll();
        if(cities.isEmpty())
            return ResponseEntity.noContent().build();
        else
            return ResponseEntity.ok(cities);
    }
}
