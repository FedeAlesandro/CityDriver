package net.avalith.carDriver.controllers;

import net.avalith.carDriver.models.Country;
import net.avalith.carDriver.models.CountryDto;
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
import java.util.stream.Collectors;

@RestController
@RequestMapping("/countries")
public class CountryController {

    @Autowired
    private CountryService countryService;

    @PostMapping("/")
    public ResponseEntity<CountryDto> save(@RequestBody @Valid Country country){
        CountryDto response = new CountryDto(countryService.save(country));
        response.setName(response.getName().replace("-", " "));

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/")
    public ResponseEntity<List<CountryDto>> getAll(){
        List<Country> countries = countryService.getAll();

        if (countries.isEmpty())
            return ResponseEntity.noContent().build();

        List<CountryDto> response = countries.stream()
                .map(CountryDto::new)
                .collect(Collectors.toList());

        response.forEach(country -> country.setName(country.getName().replace("-", " ")));

        return ResponseEntity.ok(response);
    }

    @PutMapping("/{name}/")
    public ResponseEntity<CountryDto> update(@PathVariable(value = "name") String name, @RequestBody @Valid Country country){
        CountryDto response = new CountryDto(countryService.update(name, country));
        response.setName(response.getName().replace("-", " "));

        return ResponseEntity.ok(response);
    }
}
