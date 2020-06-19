package net.avalith.carDriver.services;

import net.avalith.carDriver.exceptions.AlreadyExistsException;
import net.avalith.carDriver.exceptions.NotFoundException;
import net.avalith.carDriver.models.Country;
import net.avalith.carDriver.repositories.CountryRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static net.avalith.carDriver.utils.Constants.COUNTRY_ALREADY_EXISTS;
import static net.avalith.carDriver.utils.Constants.NOT_FOUND_COUNTRY;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CountryServiceTest {

    CountryService countryService;

    @Mock
    CountryRepository countryRepository;

    @BeforeEach
    public void setUp(){
        countryService = new CountryService(countryRepository);
    }

    @Test
    public void saveAlreadyExistsException(){
        when(countryRepository.findByName("Argentina")).thenReturn(Optional.of(new Country("Argentina")));

        AlreadyExistsException ex = Assertions.assertThrows(AlreadyExistsException.class, () -> countryService.save(new Country("Argentina")));
        Assertions.assertEquals(ex, new AlreadyExistsException(COUNTRY_ALREADY_EXISTS));
    }

    @Test
    public void saveTest(){
        Country country = new Country("Argentina");

        when(countryRepository.findByName("Argentina")).thenReturn(Optional.empty());
        when(countryRepository.save(new Country("Argentina"))).thenReturn(country);

        Assertions.assertEquals(country, countryService.save(new Country("Argentina")));
    }

    @Test
    public void getAllTest(){
        List<Country>countries = new ArrayList<>();
        when(countryRepository.findAll()).thenReturn(countries);

        Assertions.assertNotNull(countryService.getAll());
    }

    @Test
    public void updateNotFoundException(){
        Country country = new Country("Argentina");

        when(countryRepository.findByName("Uruguay")).thenReturn(Optional.empty());

        NotFoundException ex = Assertions.assertThrows(NotFoundException.class, () -> countryService.update("Uruguay", country));
        Assertions.assertEquals(ex, new NotFoundException(NOT_FOUND_COUNTRY));
    }

    @Test
    public void updateAlreadyExistsException(){
        Country country = new Country("Argentina");

        when(countryRepository.findByName("Uruguay")).thenReturn(Optional.of(new Country()));
        when(countryRepository.findByName("Argentina")).thenReturn(Optional.of(country));

        AlreadyExistsException ex = Assertions.assertThrows(AlreadyExistsException.class, () -> countryService.update("Uruguay", country));
        Assertions.assertEquals(ex, new AlreadyExistsException(COUNTRY_ALREADY_EXISTS));
    }

    @Test
    public void updateTest(){
        Country country = new Country("Argentina");

        when(countryRepository.findByName("Uruguay")).thenReturn(Optional.of(new Country()));
        when(countryRepository.findByName("Argentina")).thenReturn(Optional.empty());
        when(countryRepository.save(country)).thenReturn(country);

        Assertions.assertEquals(country, countryService.update("Uruguay", country));
    }
}
