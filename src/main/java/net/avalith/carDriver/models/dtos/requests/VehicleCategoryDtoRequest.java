package net.avalith.carDriver.models.dtos.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VehicleCategoryDtoRequest {

    @NotBlank(message = "The name is required")
    private String name;

    @NotNull(message = "The commission is required")
    private Double commission;

}
