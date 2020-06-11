package net.avalith.carDriver.models.dtos.requests;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.avalith.carDriver.models.Ride;
import net.avalith.carDriver.models.dtos.RidePointDto;
import net.avalith.carDriver.models.enums.RideState;
import net.avalith.carDriver.models.enums.TariffType;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class RideDtoUpdateRequest {

    @NotNull(message = "The start date is required")
    private Date startDate;

    @NotNull(message = "The end date is required")
    private Date endDate;

    @NotNull(message = "The state is required")
    private RideState state;

    @NotBlank(message = "The vehicle domain is required")
    private String vehicleDomain;

    @NotNull(message = "The tariff type is required")
    private TariffType tariffType;

    @NotNull(message = "The price is required")
    private Double price;

    @NotNull(message = "The origin point is required")
    private RidePointDto originPoint;

    @NotNull(message = "The destination point is required")
    private RidePointDto destinationPoint;

    @NotBlank(message = "The user dni is required")
    private String userDni;
}
