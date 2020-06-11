package net.avalith.carDriver.models.dtos.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VehicleCategoryDtoRequest {

    private String name;

    private Double commission;
}
