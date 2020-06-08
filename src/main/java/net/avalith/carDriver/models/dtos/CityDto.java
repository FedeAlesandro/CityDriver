package net.avalith.carDriver.models.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.avalith.carDriver.models.City;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
public class CityDto {

    @NotBlank(message = "The name is required")
    private String name;

    @NotBlank(message = "The country name is required")
    private String countryName;

    public CityDto(City city) {
        name = city.getName();
        countryName = city.getCountry().getName();
    }
}
