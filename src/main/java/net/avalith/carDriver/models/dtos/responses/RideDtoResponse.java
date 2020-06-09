package net.avalith.carDriver.models.dtos.responses;

import lombok.Data;
import lombok.NoArgsConstructor;
import net.avalith.carDriver.models.Ride;
import net.avalith.carDriver.models.dtos.RidePointDto;
import net.avalith.carDriver.models.enums.RideState;
import net.avalith.carDriver.models.enums.TariffType;

import java.util.Date;

@Data
@NoArgsConstructor
public class RideDtoResponse {

    private Date startDate;

    private Date endDate;

    private RideState state;

    private String vehicleDomain;

    private TariffType tariffType;

    private Double price;

    private RidePointDto originPoint;

    private RidePointDto destinationPoint;

    private String userDni;

    public RideDtoResponse(Ride ride) {
        this.startDate = ride.getStartDate();
        this.endDate = ride.getEndDate();
        this.state = ride.getState();
        this.vehicleDomain = ride.getVehicle().getDomain();
        this.tariffType = ride.getTariffType();
        this.price = ride.getPrice();
        this.originPoint = new RidePointDto(ride.getOriginPoint());
        this.destinationPoint = new RidePointDto(ride.getDestinationPoint());
        this.userDni = ride.getUser().getDni();
    }
}
