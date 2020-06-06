package net.avalith.carDriver.models.DTOS.Request;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.avalith.carDriver.models.Colors;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VehicleDTO {

    private String domain;

    private Boolean available;

    private Colors color;

    private String nameModel;

    private String  nameProvider;

    private String  nameCategory;

}
