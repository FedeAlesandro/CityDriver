package net.avalith.carDriver.models.dtos.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.avalith.carDriver.models.Provider;
import net.avalith.carDriver.models.Vehicle;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProviderDtoResponse {

    private String name;

    private List<Vehicle> vehicles = new ArrayList<>();

    public ProviderDtoResponse(Provider provider){
        this.name = provider.getName();
        this.vehicles = provider.getVehicles();
    }
}
