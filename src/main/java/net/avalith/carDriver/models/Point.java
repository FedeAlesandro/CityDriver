package net.avalith.carDriver.models;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.avalith.carDriver.models.dtos.requests.PointDtoRequest;
import net.avalith.carDriver.models.dtos.responses.PointDtoResponse;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.Date;

@Data
@Entity
@Builder
@Table(name = "points")
@NoArgsConstructor
@AllArgsConstructor
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class Point {

    @Id
    @Column(name = "id_point")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "is_origin")
    private Boolean isOrigin;

    @Column(name = "is_destination")
    private Boolean isDestination;

    @Column(name = "coordinate_latitude")
    private String coordinateLatitude;

    @Column(name = "coordinate_longitude")
    private String coordinateLongitude;

    @Column(name = "capacity")
    private Integer capacity;

    @Column(name = "stock")
    private Integer stock;

    @Column(name = "deleted_at")
    private Date deletedAt;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_city", referencedColumnName = "id_city")
    private City city;

    public static Point fromPointDtoRequest(PointDtoRequest pointDtoRequest, City city){
        return Point.builder()
                .isOrigin(pointDtoRequest.getIsOrigin())
                .coordinateLatitude(pointDtoRequest.getCoordinateLatitude())
                .coordinateLongitude(pointDtoRequest.getCoordinateLongitude())
                .capacity(pointDtoRequest.getCapacity())
                .stock(pointDtoRequest.getStock())
                .city(city)
                .build();
    }
}
