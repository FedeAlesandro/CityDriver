package net.avalith.carDriver.models.dtos;

import lombok.Data;
import net.avalith.carDriver.models.Point;

@Data
public class RidePointDto {

    private String coordinateLatitude;
    private String coordinateLogitude;

    public RidePointDto(Point point) {
        this.coordinateLatitude = point.getCoordinateLatitude();
        this.coordinateLogitude = point.getCoordinateLogitude();
    }
}
