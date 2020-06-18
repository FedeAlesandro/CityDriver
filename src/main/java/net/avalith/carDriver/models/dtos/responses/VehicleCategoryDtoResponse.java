package net.avalith.carDriver.models.dtos.responses;

import lombok.Data;
import lombok.NoArgsConstructor;
import net.avalith.carDriver.models.VehicleCategory;

@Data
@NoArgsConstructor
public class VehicleCategoryDtoResponse {

    private String name;

    private Double commission;


    public VehicleCategoryDtoResponse(VehicleCategory vehicleCategory) {
        this.name = vehicleCategory.getName();
        this.commission = vehicleCategory.getCommission();
    }
}
