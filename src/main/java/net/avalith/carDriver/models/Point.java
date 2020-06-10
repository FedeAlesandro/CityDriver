package net.avalith.carDriver.models;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.avalith.carDriver.models.dtos.requests.PointDtoRequest;
import net.avalith.carDriver.models.dtos.requests.PointDtoUpdateRequest;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.sql.Timestamp;
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

    @Column(name = "is_active")
    private Boolean isActive;

    @Column(name = "created_at")
    @CreationTimestamp
    private Timestamp createdAt;

    @Column(name = "updated_at")
    @UpdateTimestamp
    private Timestamp updatedAt;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_city", referencedColumnName = "id_city")
    private City city;

    public Point (PointDtoRequest pointDtoRequest, City city){
        isOrigin = pointDtoRequest.getIsOrigin();
        coordinateLatitude = pointDtoRequest.getCoordinateLatitude();
        coordinateLongitude = pointDtoRequest.getCoordinateLongitude();
        capacity = pointDtoRequest.getCapacity();
        stock = pointDtoRequest.getStock();
        this.city = city;
        isActive = Boolean.TRUE;
    }

    public Point (PointDtoUpdateRequest pointDtoUpdateRequest, City city){
        isOrigin = pointDtoUpdateRequest.getIsOrigin();
        isDestination = pointDtoUpdateRequest.getIsDestination();
        coordinateLatitude = pointDtoUpdateRequest.getCoordinateLatitude();
        coordinateLongitude = pointDtoUpdateRequest.getCoordinateLongitude();
        capacity = pointDtoUpdateRequest.getCapacity();
        stock = pointDtoUpdateRequest.getStock();
        this.city = city;
    }
}
