package net.avalith.carDriver.models.dtos.responses;

import lombok.Data;
import lombok.NoArgsConstructor;
import net.avalith.carDriver.models.Vehicle;
import net.avalith.carDriver.models.VehicleCategory;

import java.util.List;

@Data
@NoArgsConstructor
public class VehicleCategoryDtoResponse {

    private String name;

    private Double commission;

    private List<Vehicle> vehicles;

    public VehicleCategoryDtoResponse(VehicleCategory vehicleCategory) {
        this.name = vehicleCategory.getName();
        this.commission = vehicleCategory.getCommission();
        this.vehicles = vehicleCategory.getVehicles();
    }
}
