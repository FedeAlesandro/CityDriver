package net.avalith.carDriver.controllers;

import net.avalith.carDriver.models.City;
import net.avalith.carDriver.models.dtos.CityDto;
import net.avalith.carDriver.services.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/cities")
public class CityController {

    @Autowired
    private CityService cityService;

    @PostMapping("/")
    public ResponseEntity<CityDto> save(@RequestBody @Valid CityDto city){
        return ResponseEntity.status(HttpStatus.CREATED).body(new CityDto(cityService.save(city)));
    }

    @GetMapping("/")
    public ResponseEntity<List<CityDto>> getAll(){
        List<City> citiesAux = cityService.getAll();
        if(citiesAux.isEmpty())
            return ResponseEntity.noContent().build();
        else{
            List<CityDto> cities = citiesAux.stream()
                    .map(CityDto::new)
                    .collect(Collectors.toList());
            return ResponseEntity.ok(cities);
        }
    }

    @PutMapping("/{name}/")
    public ResponseEntity<CityDto> update(@PathVariable(value = "name") String name, @RequestBody @Valid CityDto city){
        return ResponseEntity.ok(new CityDto(cityService.update(name, city)));
    }
}
