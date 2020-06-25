package net.avalith.carDriver.factoryService;

import net.avalith.carDriver.models.City;
import net.avalith.carDriver.models.Country;
import net.avalith.carDriver.models.Point;
import net.avalith.carDriver.models.dtos.CityDto;
import net.avalith.carDriver.models.dtos.requests.PointDtoRequest;
import net.avalith.carDriver.models.dtos.requests.PointDtoUpdateRequest;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

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

    default PointDtoRequest createPointDtoRequest(){
        return new PointDtoRequest(false, "30", "20", 20, 15, "Mar del Plata");
    }

    default PointDtoUpdateRequest createPointDtoUpdateRequest(){
        return new PointDtoUpdateRequest(false, false, "30", "20", 20, 15, "Mar del Plata");
    }

    default Point createPoint(){
        return new Point(createPointDtoRequest(), createCity());
    }
}
