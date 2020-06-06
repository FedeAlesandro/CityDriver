package net.avalith.carDriver.models.dtos;

import lombok.Data;
import net.avalith.carDriver.models.City;

@Data
public class CityDto {

    private String name;

    private String countryName;

    public CityDto(City city) {
        name = city.getName();
        countryName = city.getCountry().getName();
    }
}
