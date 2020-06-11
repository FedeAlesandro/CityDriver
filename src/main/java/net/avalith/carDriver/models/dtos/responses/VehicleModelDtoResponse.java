package net.avalith.carDriver.models.dtos.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.avalith.carDriver.models.VehicleModels;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VehicleModelDtoResponse {

    private String name;

    private Integer cantPlace;

    private Boolean isAutomatic;

    private String nameBrand;

    public VehicleModelDtoResponse(VehicleModels vehicleModels) {
        VehicleModelDtoResponse.builder()
                .name(vehicleModels.getName())
                .cantPlace(vehicleModels.getCantPlace())
                .isAutomatic(vehicleModels.getIsAutomatic())
                .nameBrand(vehicleModels.getBrand().getName())
                .build();
    }
}
