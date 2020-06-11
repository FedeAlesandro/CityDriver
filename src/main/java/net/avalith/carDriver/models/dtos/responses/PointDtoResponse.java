package net.avalith.carDriver.models.dtos.responses;

import lombok.Data;
import lombok.NoArgsConstructor;
import net.avalith.carDriver.models.Point;

@Data
@NoArgsConstructor
public class PointDtoResponse {

    private Boolean isOrigin;

    private Boolean isDestination;

    private String lat;

    private String lng;

    private Integer capacity;

    private Integer stock;

    private String cityName;

    public PointDtoResponse(Point point) {
        isOrigin = point.getIsOrigin();
        isDestination = point.getIsDestination();
        lat = point.getLat();
        lng = point.getLng();
        capacity = point.getCapacity();
        stock = point.getStock();
        cityName = point.getCity().getName();
    }
}
