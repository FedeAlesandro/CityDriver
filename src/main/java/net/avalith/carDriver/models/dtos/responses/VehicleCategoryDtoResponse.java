package net.avalith.carDriver.models.dtos.responses;

import lombok.Data;
import lombok.NoArgsConstructor;
import net.avalith.carDriver.models.VehicleCategory;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class VehicleCategoryDtoResponse {

    private String name;

    private Double commission;

    private Double priceHour;

    private Double priceDay;

    private Double priceWeek;


    public VehicleCategoryDtoResponse(VehicleCategory vehicleCategory) {
        this.name = vehicleCategory.getName();
        this.commission = vehicleCategory.getCommission();
        this.priceHour = vehicleCategory.getPriceHour();
        this.priceDay = vehicleCategory.getPriceDay();
        this.priceWeek = vehicleCategory.getPriceWeek();
    }
}
