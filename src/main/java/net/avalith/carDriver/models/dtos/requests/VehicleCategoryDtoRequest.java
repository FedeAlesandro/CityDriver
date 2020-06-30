package net.avalith.carDriver.models.dtos.requests;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class VehicleCategoryDtoRequest {

    @NotBlank(message = "The name is required")
    private String name;

    @NotNull(message = "The commission is required")
    private Double commission;

    @NotNull(message = "The price for hour is required")
    private Double priceHour;

    @NotNull(message = "The price for day is required")
    private Double priceDay;

    @NotNull(message = "The price for week is required")
    private Double priceWeek;

}
