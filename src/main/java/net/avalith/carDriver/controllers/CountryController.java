package net.avalith.carDriver.controllers;

import net.avalith.carDriver.models.Country;
import net.avalith.carDriver.models.dtos.CityDto;
import net.avalith.carDriver.services.CountryService;
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

@RestController
@RequestMapping("/countries")
public class CountryController {

    @Autowired
    private CountryService countryService;

    @PostMapping("/")
    public ResponseEntity<Country> save(@RequestBody @Valid Country country){
        return ResponseEntity.status(HttpStatus.CREATED).body(countryService.save(country));
    }

    @GetMapping("/")
    public ResponseEntity<List<Country>> getAll(){
        List<Country> countries = countryService.getAll();
        if (countries.isEmpty())
            return ResponseEntity.noContent().build();
        else
            return ResponseEntity.ok(countries);
    }

    @PutMapping("/{name}/")
    public ResponseEntity<Country> update(@PathVariable(value = "name") String name, @RequestBody @Valid Country country){
        return ResponseEntity.ok(countryService.update(name, country));
    }
}
