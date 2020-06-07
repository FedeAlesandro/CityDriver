package net.avalith.carDriver.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.avalith.carDriver.models.dtos.PointDto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Data
@Entity
@Builder
@Table(name = "points")
@NoArgsConstructor
@AllArgsConstructor
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

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_city", referencedColumnName = "id_city")
    private City city;

    public static Point fromPointDto(PointDto pointDto, City city){
        return Point.builder()
                .isOrigin(pointDto.getIsOrigin())
                .isDestination(pointDto.getIsDestination())
                .coordinateLatitude(pointDto.getCoordinateLatitude())
                .coordinateLongitude(pointDto.getCoordinateLongitude())
                .capacity(pointDto.getCapacity())
                .stock(pointDto.getStock())
                .city(city)
                .build();
    }
}
