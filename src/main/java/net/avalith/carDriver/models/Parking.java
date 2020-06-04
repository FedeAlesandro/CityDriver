package net.avalith.carDriver.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "parkings")
@NoArgsConstructor
@AllArgsConstructor
public class Parking {

    @Id
    @Column(name = "id_parking")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "coordinate_latitude")
    private String coordinateLatitude;

    @Column(name = "coordinate_logitude")
    private String coordinateLogitude;

    @Column(name = "capacity")
    private Integer capacity;

    @Column(name = "vehicles_amount")
    private Integer vehiclesAmount;
}
