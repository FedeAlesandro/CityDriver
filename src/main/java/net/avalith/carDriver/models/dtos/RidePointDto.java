package net.avalith.carDriver.models.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;
import net.avalith.carDriver.models.Point;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
public class RidePointDto {

    @NotBlank(message = "The latitude coordinate is required")
    private String coordinateLatitude;

    @NotBlank(message = "The longitude coordinate is required")
    private String coordinateLongitude;

    public RidePointDto(Point point) {
        this.coordinateLatitude = point.getCoordinateLatitude();
        this.coordinateLongitude = point.getCoordinateLongitude();
    }
}
