package net.avalith.carDriver.models.dtos.requests;

import lombok.Data;
import net.avalith.carDriver.models.Ride;
import net.avalith.carDriver.models.dtos.RidePointDto;
import net.avalith.carDriver.models.enums.RideState;
import net.avalith.carDriver.models.enums.TariffType;

import java.util.Date;

@Data
public class RideDtoRequest {

    private Date startDate;

    private RideState state;

    private String vehicleDomain;

    private TariffType tariffType;

    private Double price;

    private RidePointDto originPoint;

    private String userDni;

    public RideDtoRequest(Ride ride) {
        this.startDate = ride.getStartDate();
        this.state = ride.getState();
        this.vehicleDomain = ride.getVehicle().getDomain();
        this.tariffType = ride.getTariffType();
        this.price = ride.getPrice();
        this.originPoint = new RidePointDto(ride.getOriginPoint());
        this.userDni = ride.getUser().getDni();
    }
}
