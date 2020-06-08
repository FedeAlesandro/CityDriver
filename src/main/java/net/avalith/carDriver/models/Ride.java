package net.avalith.carDriver.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Builder;
import lombok.Data;
import net.avalith.carDriver.models.dtos.RidePointDto;
import net.avalith.carDriver.models.dtos.requests.RideDtoRequest;
import net.avalith.carDriver.models.enums.RideState;
import net.avalith.carDriver.models.enums.TariffType;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.Date;

@Data
@Entity
@Builder
@Table(name = "rides")
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class Ride {

    @Id
    @Column(name = "id_ride")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "start_date")
    private Date startDate;

    @Column(name = "end_date")
    private Date endDate;

    @Enumerated(EnumType.STRING)
    private RideState state;

    @Column(name = "code")
    private String code;

    @Enumerated(EnumType.STRING)
    private TariffType tariffType;

    @Column(name = "price")
    private Double price;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_vehicle", referencedColumnName = "id_vehicle")
    @JsonIgnore
    private Vehicle vehicle;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_origin_point", referencedColumnName = "id_point")
    @JsonIgnore
    private Point originPoint;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_destination_point", referencedColumnName = "id_point")
    @JsonIgnore
    private Point destinationPoint;

    @OneToOne
    @JoinColumn(name = "id_user")
    private User user;

    public static Ride fromRideDtoRequest(RideDtoRequest rideDto, Vehicle vehicle, Point point, User user){
        return Ride.builder()
                .startDate(rideDto.getStartDate())
                .state(RideState.reserved)
                .tariffType(rideDto.getTariffType())
                .price(rideDto.getPrice())
                .vehicle(vehicle)
                .originPoint(point)
                .user(user)
                .build();
    }
}
