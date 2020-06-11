package net.avalith.carDriver.models.dtos.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import net.avalith.carDriver.models.Brand;
import net.avalith.carDriver.models.VehicleModels;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BrandDtoResponse {

    private String name;

    private List<VehicleModels> vehicle_models;

    public BrandDtoResponse(Brand brand) {
        this.name = brand.getName();
        this.vehicle_models = brand.getVehicleModels();
    }
}
