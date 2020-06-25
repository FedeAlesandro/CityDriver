package net.avalith.carDriver.factoryService;

import net.avalith.carDriver.models.City;
import net.avalith.carDriver.models.Country;
import net.avalith.carDriver.models.Provider;
import net.avalith.carDriver.models.dtos.CityDto;
import net.avalith.carDriver.models.dtos.requests.BrandDtoRequest;
import net.avalith.carDriver.models.dtos.requests.ProviderDtoRequest;

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

    default ProviderDtoRequest createProviderDto (){
       return new ProviderDtoRequest(
                "Car One", "car@hotmail.com","car S.A.",
                "457812","123456");
    }

    default BrandDtoRequest createBrandDto(){
        return new BrandDtoRequest("name",Boolean.TRUE);
    }

    default Provider createProvider(){
        return new Provider("toyoya");
    }
}
