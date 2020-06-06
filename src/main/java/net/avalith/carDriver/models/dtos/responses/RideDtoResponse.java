package net.avalith.carDriver.models.dtos.responses;

import net.avalith.carDriver.models.dtos.RidePointDto;
import net.avalith.carDriver.models.enums.RideState;
import net.avalith.carDriver.models.enums.TariffType;

import java.util.Date;

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
}
