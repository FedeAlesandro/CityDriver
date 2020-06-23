package net.avalith.carDriver.factoryService;

import net.avalith.carDriver.models.City;
import net.avalith.carDriver.models.Country;
import net.avalith.carDriver.models.dtos.CityDto;

public interface FactoryService {

    default CityDto createCityDto(){
        return new CityDto("Mar del Plata", "Argentina");
    }

    default CityDto createCityDtoAnotherName(){
        return new CityDto("Buenos Aires", "Argentina");
    }

    default Country createCountry(){
        return new Country("Argentina");
    }

    default City createCity(){
        return new City(createCityDto(), createCountry());
    }

    default City createCityAnotherName(){
        return new City(createCityDtoAnotherName(), createCountry());
    }
}
