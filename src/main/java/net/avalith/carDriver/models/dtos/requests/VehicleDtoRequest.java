package net.avalith.carDriver.models.dtos.requests;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.avalith.carDriver.models.enums.Colors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class VehicleDtoRequest {

    private String domain;

    private Colors color;

    private String nameModel;

    private String  nameProvider;

    private String  nameCategory;

}
