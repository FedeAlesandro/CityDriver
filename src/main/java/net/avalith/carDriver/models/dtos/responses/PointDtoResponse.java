package net.avalith.carDriver.models.dtos.responses;

import lombok.Data;
import lombok.NoArgsConstructor;
import net.avalith.carDriver.models.Point;

@Data
@NoArgsConstructor
public class PointDtoResponse {

    private Boolean isOrigin;

    private Boolean isDestination;

    private String coordinateLatitude;

    private String coordinateLongitude;

    private Integer capacity;

    private Integer stock;

    private String cityName;

    public PointDtoResponse(Point point) {
        isOrigin = point.getIsOrigin();
        isDestination = point.getIsDestination();
        coordinateLatitude = point.getCoordinateLatitude();
        coordinateLongitude = point.getCoordinateLongitude();
        capacity = point.getCapacity();
        stock = point.getStock();
        cityName = point.getCity().getName();
    }
}