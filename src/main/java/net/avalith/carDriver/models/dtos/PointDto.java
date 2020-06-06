package net.avalith.carDriver.models.dtos;

import lombok.Data;
import net.avalith.carDriver.models.Point;

@Data
public class PointDto {

    private Boolean isOrigin;

    private Boolean isDestination;

    private String coordinateLatitude;

    private String coordinateLogitude;

    private Integer capacity;

    private Integer stock;

    private String cityName;

    public PointDto(Point point) {
        isOrigin = point.getIsOrigin();
        isDestination = point.getIsDestination();
        coordinateLatitude = point.getCoordinateLatitude();
        coordinateLogitude = point.getCoordinateLogitude();
        capacity = point.getCapacity();
        stock = point.getStock();
        cityName = point.getCity().getName();
    }
}
