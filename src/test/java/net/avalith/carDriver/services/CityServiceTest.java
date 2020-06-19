package net.avalith.carDriver.services;

import net.avalith.carDriver.exceptions.AlreadyExistsException;
import net.avalith.carDriver.exceptions.NotFoundException;
import net.avalith.carDriver.models.City;
import net.avalith.carDriver.models.Country;
import net.avalith.carDriver.models.dtos.CityDto;
import net.avalith.carDriver.repositories.CityRepository;
import net.avalith.carDriver.repositories.CountryRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static net.avalith.carDriver.utils.Constants.CITY_ALREADY_EXISTS;
import static net.avalith.carDriver.utils.Constants.NOT_FOUND_CITY;
import static net.avalith.carDriver.utils.Constants.NOT_FOUND_COUNTRY;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CityServiceTest {

    CityService cityService;

    @Mock
    CityRepository cityRepository;

    @Mock
    CountryRepository countryRepository;

    @BeforeEach
    public void setUp(){
        cityService = new CityService(cityRepository, countryRepository);
    }

    @Test
    public void getAllTest(){
        List<City> cities = new ArrayList<>();
        when(cityRepository.findAll()).thenReturn(cities);
        Assertions.assertNotNull(cityService.getAll());
    }

    @Test
    public void saveCountryNotFound(){
        CityDto cityDto = new CityDto("Mar del Plata", "Argentina");

        when(countryRepository.findByName("Argentina")).thenReturn(Optional.empty());

        NotFoundException  ex = Assertions.assertThrows(NotFoundException.class, () -> cityService.save(cityDto));
        Assertions.assertEquals(ex, new NotFoundException(NOT_FOUND_COUNTRY));
    }

    @Test
    public void saveCityAlreadyExists(){
        CityDto cityDto = new CityDto("Mar del Plata", "Argentina");

        when(countryRepository.findByName("Argentina")).thenReturn(Optional.of(new Country()));

        when(cityRepository.findByName("Mar del Plata")).thenReturn(Optional.of(new City()));

        AlreadyExistsException  ex = Assertions.assertThrows(AlreadyExistsException.class, () -> cityService.save(cityDto));
        Assertions.assertEquals(ex, new AlreadyExistsException(CITY_ALREADY_EXISTS));
    }

    @Test
    public void saveTest(){

        CityDto cityDto = new CityDto("Mar del Plata", "Argentina");
        Country country = new Country("Argentina");
        City city = new City(cityDto, country);

        when(countryRepository.findByName("Argentina")).thenReturn(Optional.of(country));

        when(cityRepository.findByName("Mar del Plata")).thenReturn(Optional.empty());

        when(cityRepository.save(new City(cityDto, country))).thenReturn(city);

        Assertions.assertEquals(city, cityService.save(cityDto));
    }

    @Test
    public void updateTest(){
        Country country = new Country("Argentina");
        City oldCity = new City("Mar del Plata", country);
        CityDto cityDto = new CityDto(new City("Buenos Aires", country));
        City newCity = new City("Buenos Aires", country);

        when(cityRepository.findByName("Mar del Plata")).thenReturn(Optional.of(oldCity));
        when(cityRepository.findByName(cityDto.getName())).thenReturn(Optional.empty());
        when(countryRepository.findByName(cityDto.getCountryName())).thenReturn(Optional.of(country));
        when(cityRepository.save(new City(cityDto, country))).thenReturn(newCity);

        Assertions.assertEquals(newCity, cityService.update("Mar del Plata", cityDto));
    }

    @Test
    public void updateNotFoundException(){
        CityDto cityDto = new CityDto(new City("Buenos Aires", new Country("Argentina")));

        when(cityRepository.findByName("Mar del Plata")).thenReturn(Optional.empty());

        NotFoundException  ex = Assertions.assertThrows(NotFoundException.class, () -> cityService.update("Mar del Plata", cityDto));

        Assertions.assertEquals(ex, new NotFoundException(NOT_FOUND_COUNTRY));
    }

    @Test
    public void updateAlreadyExistsException(){
        Country country = new Country("Argentina");
        CityDto cityDto = new CityDto(new City("Buenos Aires", country));

        when(cityRepository.findByName("Mar del Plata")).thenReturn(Optional.of(new City()));
        when(cityRepository.findByName(cityDto.getName())).thenReturn(Optional.of(new City(cityDto, country)));

        AlreadyExistsException  ex = Assertions.assertThrows(AlreadyExistsException.class, () -> cityService.update("Mar del Plata", cityDto));

        Assertions.assertEquals(ex, new AlreadyExistsException(CITY_ALREADY_EXISTS));
    }

    @Test
    public void updateCountryNotFoundException(){
        Country country = new Country("Argentina");
        City oldCity = new City("Mar del Plata", country);
        CityDto cityDto = new CityDto(new City("Buenos Aires", country));

        when(cityRepository.findByName("Mar del Plata")).thenReturn(Optional.of(oldCity));
        when(cityRepository.findByName(cityDto.getName())).thenReturn(Optional.empty());
        when(countryRepository.findByName(cityDto.getCountryName())).thenReturn(Optional.empty());

        NotFoundException  ex = Assertions.assertThrows(NotFoundException.class, () -> cityService.update("Mar del Plata", cityDto));

        Assertions.assertEquals(ex, new NotFoundException(NOT_FOUND_COUNTRY));
    }
}
