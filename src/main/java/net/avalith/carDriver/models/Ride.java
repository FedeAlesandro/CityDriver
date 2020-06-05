package net.avalith.carDriver.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import net.avalith.carDriver.models.enums.RideState;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import java.util.Date;

public class Ride {

    @Id
    @Column(name = "id_ride")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "start_date")
    private Date startDate;

    @Column(name = "end_date")
    private Date endDate;

    @Column(name = "state")
    private RideState state;

    @Column(name = "code")
    private String code;

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

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_tariff", referencedColumnName = "id_tariff")
    @JsonIgnore
    private Tariff tariff;

    @OneToOne
    @JoinColumn(name = "id_user")
    private User user;
}
