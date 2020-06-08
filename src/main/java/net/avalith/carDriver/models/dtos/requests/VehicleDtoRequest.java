package net.avalith.carDriver.models.dtos.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.avalith.carDriver.models.enums.Colors;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VehicleDtoRequest {

    private String domain;

    private Colors color;

    private String nameModel;

    private String  nameProvider;

    private String  nameCategory;

}
