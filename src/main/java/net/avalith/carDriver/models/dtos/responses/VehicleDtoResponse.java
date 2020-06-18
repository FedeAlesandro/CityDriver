package net.avalith.carDriver.models.dtos.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.avalith.carDriver.models.Vehicle;
import net.avalith.carDriver.models.enums.Colors;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VehicleDtoResponse {

    private String domain;

    private Colors color;

    private String nameModel;

    private String  nameProvider;

    private String  nameCategory;

    public VehicleDtoResponse(Vehicle vehicle){
        this.domain = vehicle.getDomain();
        this.color = vehicle.getColor();
        this.nameModel = vehicle.getVehicleModels().getName();
        this.nameProvider = vehicle.getProvider().getName();
        this.nameCategory = vehicle.getCategoryVehicles().getName();
    }
}
