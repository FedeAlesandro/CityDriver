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

}
