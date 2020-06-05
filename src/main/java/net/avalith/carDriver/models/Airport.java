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
@Table(name = "airports")
@NoArgsConstructor
@AllArgsConstructor
public class Airport {

    @Id
    @Column(name = "id_airport")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "coordinate_latitude")
    private String coordinateLatitude;

    @Column(name = "coordinate_logitude")
    private String coordinateLogitude;
}
