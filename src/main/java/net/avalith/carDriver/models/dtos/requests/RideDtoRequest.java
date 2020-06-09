package net.avalith.carDriver.models.dtos.requests;

import lombok.Data;
import lombok.NoArgsConstructor;
import net.avalith.carDriver.models.Ride;
import net.avalith.carDriver.models.dtos.RidePointDto;
import net.avalith.carDriver.models.enums.TariffType;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@NoArgsConstructor
public class RideDtoRequest {

    @NotNull(message = "The start date is required")
    private Date startDate;

    @NotBlank(message = "The vehicle domain is required")
    private String vehicleDomain;

    @NotNull(message = "The tariff type is required")
    private TariffType tariffType;

    @NotNull(message = "The price is required")
    private Double price;

    @NotNull(message = "The origin point is required")
    private RidePointDto originPoint;

    @NotBlank(message = "The user dni is required")
    private String userDni;

    public RideDtoRequest(Ride ride) {
        this.startDate = ride.getStartDate();
        this.vehicleDomain = ride.getVehicle().getDomain();
        this.tariffType = ride.getTariffType();
        this.price = ride.getPrice();
        this.originPoint = new RidePointDto(ride.getOriginPoint());
        this.userDni = ride.getUser().getDni();
    }
}
